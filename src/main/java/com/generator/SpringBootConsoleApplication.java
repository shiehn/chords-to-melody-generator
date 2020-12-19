package com.generator;

import com.generator.chords.ChordsLSTM;
import com.generator.melody.Config;
import com.generator.melody.MelodyLSTM;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

public class SpringBootConsoleApplication {}

//@SpringBootApplication
//public class SpringBootConsoleApplication implements CommandLineRunner {
//
//    @Value("${forcecorrectpitches}")
//    private Boolean forceCorrectPitches;
//
//    public static void main(String[] args) throws Exception {
//        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//
////        if(args == null || args.length < 1) {
////            System.out.println("please run with flags:");
////            System.out.println("-mode=melody -chords=31^313*313*313*313*613*613*715*715*");
////            System.out.println("-mode=chords -chords=#31:313-313%");
////            exit(1);
////        }
////
////        System.out.println(args[0]);
//
//        args = new String[2];
//        args[0] = "-mode=melody";
//        args[1] = "-chords=31^313*313*313*313*613*613*715*715*";
//
//        if(args[0].split("=")[1].equalsIgnoreCase("chords")){
//
//            //#31:310-610%310*610*310*610#31:310-610%310*10*310*610#31:310-610%310*610*310*613#31:310-610%310*613*613*610#31:310-613%613*610*31
//
//            System.out.println("TRAINING *CHORDS* MODEL");
//
//            System.out.println("KEY AND CHORDS = " + args[1].split("=")[1]);
//
//            String keyAndChords = args[1].split("=")[1];
//
//            if(keyAndChords.length() != 12){
//                System.out.println("please run with flag: -mode=chords -chords=#31:313-313%");
//                exit(1);
//            }
//
//            Config config = new Config(forceCorrectPitches);
//            ChordsLSTM lstm = new ChordsLSTM(config);
//            lstm.run(keyAndChords);
//            exit(0);
//        } else {
//            System.out.println("TRAINING *MELODY* MODEL");
//
//            System.out.println("KEY AND CHORDS = " + args[1].split("=")[1]);
//
//            String keyAndChords = args[1].split("=")[1];
//
//            if(keyAndChords.length() != 35){
//                System.out.println("please run with flag: -chords=31^313*313*313*313*613*613*715*715*");
//                exit(1);
//            }
//
//            Config config = new Config(forceCorrectPitches);
//            MelodyLSTM lstm = new MelodyLSTM(config);
//            lstm.run(keyAndChords);
//            exit(0);
//        }
//    }
//}
