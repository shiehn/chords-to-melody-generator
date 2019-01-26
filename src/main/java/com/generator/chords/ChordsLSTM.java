package com.generator.chords;

import com.generator.decoder.ChordMelodyDecoder;
import com.generator.melody.Config;
import org.deeplearning4j.api.storage.StatsStorage;
import org.deeplearning4j.nn.api.Layer;
import org.deeplearning4j.nn.conf.BackpropType;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.GravesLSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.api.IterationListener;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.ui.api.UIServer;
import org.deeplearning4j.ui.stats.StatsListener;
import org.deeplearning4j.ui.storage.InMemoryStatsStorage;
import org.deeplearning4j.util.ModelSerializer;
import org.jfugue.midi.MidiFileManager;
import org.jfugue.pattern.Pattern;
import org.jfugue.pattern.PatternProducer;
import org.jfugue.player.Player;
import org.joda.time.DateTime;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.RmsProp;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.File;
import java.util.Random;

public class ChordsLSTM {

    Config config;

    public ChordsLSTM(Config config){
        this.config = config;
    }

    //#31:310-610%310*610*310*610#31:310-610%310*10*310*610#31:310-610%310*610*310*613#31:310-610%310*613*613*610#31:310-613%613*610*31

    public void run(String keyAndChords) throws Exception {
        int lstmLayerSize = 200;                    //Number of units in each GravesLSTM layer
        int miniBatchSize = 32;             //Size of mini batch to use when  training
        int exampleLength = 27;//100 per row         //Length of each training example sequence to use. This could certainly be increased
        int tbpttLength = 355;                       //Length for truncated backpropagation through time. i.e., do parameter updates ever 50 characters
        int numEpochs = 4;                            //Total number of training epochs
        int generateSamplesEveryNMinibatches = 50;  //How frequently to generate samples from the network? 1000 characters / 50 tbptt length: 20 parameter updates per minibatch
        int nSamplesToGenerate = 100;                    //Number of samples to generate after each training epoch
        int nCharactersToSample = 27;                //Length of each sample to generate
        String generationInitialization = null;        //Optional character initialization; a random character is used if null
        // Above is Used to 'prime' the MelodyLSTM with a character sequence to continue/complete.
        // Initialization characters must all be in CharacterIterator.getMinimalCharacterSet() by default
        Random rng = new Random(12345);

        //Save the model
        File savedLocation = new File("trainedModel_chords_0.zip");      //Where to save the network. Note: the file is in .zip format - can be opened externally

        System.out.println("TRYING TO LOAD MODEL FROM : " + savedLocation.getAbsolutePath());
        //Load the model
        MultiLayerNetwork restored = null;
        try {
            restored = ModelSerializer.restoreMultiLayerNetwork(savedLocation);
            System.out.println("MODEL RESTORED!: " + savedLocation.getName());
        } catch (Exception e) {
            System.out.println("COULD NOT LOAD SAVED MODEL");
        }

        CharacterIterator iter = (new DataIngestor()).getCharacterIterator(miniBatchSize, exampleLength);

        if (restored != null) {
            int totalGood = 0;
            String[] samples = sampleCharactersFromNetwork(keyAndChords, generationInitialization, restored, iter, rng, nCharactersToSample, nSamplesToGenerate);
            for (int j = 0; j < samples.length; j++) {
                System.out.println();

                ChordMelodyDecoder decoder = new ChordMelodyDecoder(config);
                String validString = decoder.extractValidChords(samples[j]);

                if (validString.equalsIgnoreCase("invalid")) {
                    System.out.println("INVALID SAMPLE!! DO NOT PLAY:");
                } else {
                    totalGood++;
                    String playString = "";
                    String chordString = "";
                    String bassString = "";
                    try {
                        System.out.println("----- Sample " + j + " -----");
                        System.out.println(samples[j]);

                        playString = decoder.decodeMelody(validString, decoder.getKey(validString));
                        chordString = decoder.decodeChords(validString);
                        //bassString = decoder.decodeBassline(validString);

                        System.out.println("GOOD: " + playString);
                        System.out.println("CHRORDS: " + chordString);

                        //playString = playString.replace("5", "6");
                        //playString = playString.replace("4", "5");
                        Player player = new Player();

                        System.out.println("F");
                        String patternStr = "V0 I[Piano] " + playString.trim() + " V1 I[Piano] " + chordString.trim();
                        System.out.println(patternStr);
                        Pattern pattern = new Pattern(patternStr);
                        //player.play("V0 " + playString + " V1 " + chordString);
                        //player = null;

                        String fileName = "midi" + j + ".mid";
                        System.out.println("G");
                        MidiFileManager.savePatternToMidi((PatternProducer) pattern, new File(fileName));

                        System.out.println("H");
                        System.out.println("COMPLETE");
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }

            System.out.println("TOTAL GOOD: " + totalGood + " out of " + samples.length);
        } else {
            //Get a DataSetIterator that handles vectorization of text into something we can use to train
            // our GravesLSTM network.
            int nOut = iter.totalOutcomes();

            //Set up network configuration:
            MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                    .seed(12345)
                    .l2(0.001)
                    .weightInit(WeightInit.XAVIER)
                    .updater(new RmsProp(0.01))
                    .list()
                    .layer(0, new GravesLSTM.Builder().nIn(iter.inputColumns()).nOut(lstmLayerSize)
                            .activation(Activation.TANH).build())
                    .layer(1, new GravesLSTM.Builder().nIn(lstmLayerSize).nOut(lstmLayerSize)
                            .activation(Activation.TANH).build())
                    .layer(2, new GravesLSTM.Builder().nIn(lstmLayerSize).nOut(lstmLayerSize)
                            .activation(Activation.TANH).build())
                    .layer(3, new GravesLSTM.Builder().nIn(lstmLayerSize).nOut(lstmLayerSize)
                            .activation(Activation.TANH).build())
                    .layer(4, new GravesLSTM.Builder().nIn(lstmLayerSize).nOut(lstmLayerSize)
                            .activation(Activation.TANH).build())
                    .layer(5, new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                            .activation(Activation.SOFTMAX)        //MCXENT + softmax for classification
                            .nIn(lstmLayerSize).nOut(nOut).build())
                    .backpropType(BackpropType.Standard)
                    //.tBPTTForwardLength(tbpttLength)
                    //.tBPTTBackwardLength(tbpttLength)
                    .pretrain(false)
                    .backprop(true)
                    .build();

            MultiLayerNetwork net = new MultiLayerNetwork(conf);
            net.init();

            //UI MONITORINGS STUFF
            UIServer uiServer = UIServer.getInstance();
            StatsStorage statsStorage = new InMemoryStatsStorage();
            uiServer.attach(statsStorage);

            IterationListener[] listeners = new IterationListener[]{new ScoreIterationListener(1),new StatsListener(statsStorage)};
            net.setListeners(listeners);

            //Print the  number of parameters in the network (and for each layer)
            Layer[] layers = net.getLayers();
            int totalNumParams = 0;
            for (int i = 0; i < layers.length; i++) {
                int nParams = layers[i].numParams();
                System.out.println("Number of parameters in layer " + i + ": " + nParams);
                totalNumParams += nParams;
            }
            System.out.println("Total number of network parameters: " + totalNumParams);

            //Do training, and then generate and print samples from network
            int miniBatchNumber = 0;
            for (int i = 0; i < numEpochs; i++) {
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                System.out.println("%%%%%%%%% EPOCH: " + (i+1) + " %%%%%%%%%%%%%%");
                System.out.println("%%%%%%%%% EPOCH: " + (i+1) + " %%%%%%%%%%%%%%");
                System.out.println("%%%%%%%%% EPOCH: " + (i+1) + " %%%%%%%%%%%%%%");
                System.out.println("%%%%%%%%% EPOCH: " + (i+1) + " %%%%%%%%%%%%%%");
                System.out.println("%%%%%%%%% EPOCH: " + (i+1) + " %%%%%%%%%%%%%%");
                System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
                while (iter.hasNext()) {
                    DataSet ds = iter.next();
                    net.fit(ds);
                    if (++miniBatchNumber % generateSamplesEveryNMinibatches == 0) {
                        System.out.println("--------------------");
                        System.out.println("Completed " + miniBatchNumber + " minibatches of size " + miniBatchSize + "x" + exampleLength + " characters");
                        System.out.println("Sampling characters from network given initialization \"" + (generationInitialization == null ? "" : generationInitialization) + "\"");
                        String[] samples = sampleCharactersFromNetwork(keyAndChords, generationInitialization, net, iter, rng, nCharactersToSample, nSamplesToGenerate);

                        int totalGood = 0;

                        for (int j = 0; j < samples.length; j++) {
                            System.out.println("----- Sample " + j + " -----");
                            System.out.println(samples[j]);
                            System.out.println();

                            ChordMelodyDecoder decoder = new ChordMelodyDecoder(config);
                            String validString = decoder.extractValidChords(samples[j]);

                            if (validString.equalsIgnoreCase("invalid")) {
                                System.out.println("INVALID SAMPLE!! DO NOT PLAY:");
                            } else {
                                totalGood++;
                                System.out.println("GOOD: " + validString);
                                //player.play(playString);
                                //Thread.sleep(5000);
                            }
                        }

                        System.out.println("******************************************************");
                        System.out.println("TOTAL GOOD: " + totalGood + " out of " + samples.length);
                        System.out.println("******************************************************");
                    }
                }

                iter.reset();    //Reset iterator for another epoch
            }


            boolean saveUpdater = true;                                             //Updater: i.e., the state for Momentum, RMSProp, Adagrad etc. Save this if you want to train your network more in the future
            ModelSerializer.writeModel(net, savedLocation, saveUpdater);
        }

        System.out.println("\n\nExample complete: " + DateTime.now());
    }


    private static String[] sampleCharactersFromNetwork(String keyAndChords, String initialization, MultiLayerNetwork net,
                                                        CharacterIterator iter, Random rng, int charactersToSample, int numSamples) {
        //Set up initialization. If no initialization: use a random character
        if (initialization == null) {
            //initialization = String.valueOf(iter.getRandomCharacter());
            initialization = keyAndChords;
        }

        //Create input for initialization
        INDArray initializationInput = Nd4j.zeros(numSamples, iter.inputColumns(), initialization.length());
        char[] init = initialization.toCharArray();
        for (int i = 0; i < init.length; i++) {
            int idx = iter.convertCharacterToIndex(init[i]);
            for (int j = 0; j < numSamples; j++) {
                initializationInput.putScalar(new int[]{j, idx, i}, 1.0f);
            }
        }

        StringBuilder[] sb = new StringBuilder[numSamples];
        for (int i = 0; i < numSamples; i++) sb[i] = new StringBuilder(initialization);

        //Sample from network (and feed samples back into input) one character at a time (for all samples)
        //Sampling is done in parallel here
        net.rnnClearPreviousState();
        INDArray output = net.rnnTimeStep(initializationInput);
        output = output.tensorAlongDimension(output.size(2) - 1, 1, 0);    //Gets the last time step output

        for (int i = 0; i < charactersToSample; i++) {
            //Set up next input (single time step) by sampling from previous output
            INDArray nextInput = Nd4j.zeros(numSamples, iter.inputColumns());
            //Output is a probability distribution. Sample from this for each example we want to generate, and add it to the new input
            for (int s = 0; s < numSamples; s++) {
                double[] outputProbDistribution = new double[iter.totalOutcomes()];
                for (int j = 0; j < outputProbDistribution.length; j++)
                    outputProbDistribution[j] = output.getDouble(s, j);
                int sampledCharacterIdx = sampleFromDistribution(outputProbDistribution, rng);

                nextInput.putScalar(new int[]{s, sampledCharacterIdx}, 1.0f);        //Prepare next time step input
                sb[s].append(iter.convertIndexToCharacter(sampledCharacterIdx));    //Add sampled character to StringBuilder (human readable output)
            }

            output = net.rnnTimeStep(nextInput);    //Do one time step of forward pass
        }

        String[] out = new String[numSamples];
        for (int i = 0; i < numSamples; i++) out[i] = sb[i].toString();
        return out;
    }

    public static int sampleFromDistribution(double[] distribution, Random rng) {
        double d = 0.0;
        double sum = 0.0;
        for (int t = 0; t < 10; t++) {
            d = rng.nextDouble();
            sum = 0.0;
            for (int i = 0; i < distribution.length; i++) {
                sum += distribution[i];
                if (d <= sum) return i;
            }
            //If we haven't found the right index yet, maybe the sum is slightly
            //lower than 1 due to rounding error, so try again.
        }
        //Should be extremely unlikely to happen if distribution is a valid probability distribution
        throw new IllegalArgumentException("Distribution is invalid? d=" + d + ", sum=" + sum);
    }
}
