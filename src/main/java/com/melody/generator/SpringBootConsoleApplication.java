package com.melody.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    @Value("${forcecorrectpitches}")
    private Boolean forceCorrectPitches;

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("FORCE CORRECT: " + forceCorrectPitches);

        if(args == null || args.length < 1) {
            System.out.println("please run with flag: -chords=31^313*313*313*313*613*613*715*715*");
            exit(1);
        }

        System.out.println("KEY AND CHORDS = " + args[0].split("=")[1]);

        String keyAndChords = args[0].split("=")[1];

        if(keyAndChords.length() != 35){
            System.out.println("please run with flag: -chords=31^313*313*313*313*613*613*715*715*");
            exit(1);
        }

        Config config = new Config(forceCorrectPitches);
        LSTM lstm = new LSTM(config);
        lstm.run(keyAndChords);
        exit(0);
    }
}