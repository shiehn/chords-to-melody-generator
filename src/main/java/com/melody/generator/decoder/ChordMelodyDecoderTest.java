package com.melody.generator.decoder;

import org.junit.Assert;
import org.junit.Test;

public class ChordMelodyDecoderTest {

    String validA = "11^113*113*113*415*713*713*713*315*6250-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-1150-1151-6250-6251+5150-5151-5151-5151-5151-5151-5151-5151-4250-4251-3250-3251-2140-2141-2141-2141+6250-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251+6250-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251-6251#";
    String validB = "11^113*113*113*415*713*713*713*315*3250-3251-3251-3251-4250-4251-5150-5151-7250-7251-6250-6251-6251-6251-2140-2141+2140-2141-2141-2141-2141-2141-2141-2141-2141-2141-2141-2141-2141-2141-2141-2141+6250-6251-7250-7251-2150-2151-2151-2151-2151-2151-2151-2151-2151-2151-2151-2151+0000-0000-0000-0000-1150-1151-1151-1151-1150-1151-1151-1151-1151-1151-1150-1151#";

    @Test
    public void shouldDetermineIfValid(){

        String invalidA = "11^113*113*113*415*713*713*713*315*0000-0000-0000-0000-4260-4261-4261-4261-4260-4261-5160-5161-3160-3161-3161-3161+3260-3261-3261-3261-3261-3261-3261-3261-1250-1251-3260-3261-1250-1251-2150-2151+0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000#4000000000000000000000000000#00##0#000#00000000000000000000000000000000000000009";
        String invalidB = "11^113*113*113*415*713*713*713*315*0000-0000-0000-0000-7250-7251-7251-7251-7251-7251-7251-7251-1150-1151-1151-1151+7250-7251-7251-7251-7251-7251-7250-7251-6250-6251-6251-6251-5150-5151-5151-5151+5150-5151-5151-5151-5151-5151-5151-5151-7150-7151-7151-7151-7151-7151-2150-2151##000000000-0-0-0#000000000000000000000000000000000000000000000000000000000000000";
        String invalidC = "11^113*113*113*415*713*713*713*315*5140-5141-5141-5141-5141-5141-5141-5141-0000-0000-0000-0000-0000-0000-0000-0000+0000-0000-0000-0000+0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000#100000-000-0000000000000000000000000000000000000000000000000000##000000000000000000000000000000000000000000000000000000000000000000000000000";

        ChordMelodyDecoder chordMelodyDecoder = new ChordMelodyDecoder();
        Assert.assertFalse(chordMelodyDecoder.INVALID.equalsIgnoreCase(chordMelodyDecoder.extractValid(validA)));
        Assert.assertFalse(chordMelodyDecoder.INVALID.equalsIgnoreCase(chordMelodyDecoder.extractValid(validB)));

        Assert.assertTrue(chordMelodyDecoder.INVALID.equalsIgnoreCase(chordMelodyDecoder.extractValid(invalidA)));
        Assert.assertTrue(chordMelodyDecoder.INVALID.equalsIgnoreCase(chordMelodyDecoder.extractValid(invalidB)));
        Assert.assertTrue(chordMelodyDecoder.INVALID.equalsIgnoreCase(chordMelodyDecoder.extractValid(invalidC)));
    }

    @Test
    public void shouldProduceJfugeString(){
       String expectedA = "f#5h. a5i f#5i e5h d#5i c#5i b4q f#5w f#5w";
       String expectedB = "c#5q d#5i e5i g#5i f#5q b4i b4w f#5i g#5i b5h. r0s r0s r0s r0s a5q a5q. a5i";
       ChordMelodyDecoder decoder = new ChordMelodyDecoder();

       System.out.println("ACTUAL:   " + decoder.decodeMelody(validA));
       System.out.println("EXPECTED: " + expectedA);
       Assert.assertTrue(expectedA.trim().equalsIgnoreCase(decoder.decodeMelody(validA).trim()));

        System.out.println("ACTUAL:   " + decoder.decodeMelody(validB));
        System.out.println("EXPECTED: " + expectedB);
        Assert.assertTrue(expectedB.trim().equalsIgnoreCase(decoder.decodeMelody(validB).trim()));
    }

    /*

    THE "roi" is NOT CORRECT .. please make a test for this one

    31^310*310*111*111*411*411*710*710*6140-6141-6141-6141-6141-6141-6141-6141-6140-6141-6141-6141-6141-6141-6141-6141+2140-2141-2141-2141-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000-0000+0000-0000-1040-1041-1140-1141-3150-3151-4150-4151-4150-4151-3150-3151-3150-3151+7140-7141-7141-7141-6140-6141-4150-4151-4150-4151-4151-4151-5140-5141-5141-514##
CHORDS : 310*310*111*111*411*411*710*710
CHORDS : 310*310*111*111*411*411*710*710
GOOD: f4h f4h b4q r0s r0s r0s r0s r0s r0s r0s r0s r0s r0s r0i r0s r0s g#4i a4i c5i d5i d5i c5i c5i g4q f4i d5i d5q e4q
CHRORDS: cmajh  cmajh  aminh  aminh  dminh  dminh  gmajh  gmajh

     */




    /*
    @Test
    public void shouldGenerateValidJfugueString() {
        ChordMelodyDecoder chordMelodyDecoder = new ChordMelodyDecoder();

        String inputA = "42-500-500-5050-5051-5051-5051-7140-7141-7141-7141-2040-2041-5050-5051-5051-5051-4150-4151";
        String inputB = "42-715-715-4150-4151-4151-4151-7140-7141-7141-7141-2140-2141-4150-4151-4151-4151-3150-3151-";
        String inputC = "42-315-315-3150-3151-3151-3151-3151-3151-3151-3151-3151-3151-3151-3151-3151-3151-3151-3151-";
        String inputD = "42-400-315-3150-3151-3151-3151-2040-2041-2041-2041-1040-1041-1041-1041-7140-7141-7141-7141-";

        String expectA = "d#5q g4q a#4i d#5q d5i";
        String expectB = "d5q g4q b4i d5q c5i";
        String expectC = "c5w";
        String expectD = "c5q a#4q g#4q g4q";

        System.out.println(chordMelodyDecoder.decode(inputA));
        Assert.assertTrue(expectA.trim().equalsIgnoreCase(chordMelodyDecoder.decode(inputA).trim()));
        System.out.println(chordMelodyDecoder.decode(inputB));
        Assert.assertTrue(expectB.trim().equalsIgnoreCase(chordMelodyDecoder.decode(inputB).trim()));
        System.out.println(chordMelodyDecoder.decode(inputC));
        Assert.assertTrue(expectC.trim().equalsIgnoreCase(chordMelodyDecoder.decode(inputC).trim()));
        System.out.println(chordMelodyDecoder.decode(inputD));
        Assert.assertTrue(expectD.trim().equalsIgnoreCase(chordMelodyDecoder.decode(inputD).trim()));
    }
    */

}
