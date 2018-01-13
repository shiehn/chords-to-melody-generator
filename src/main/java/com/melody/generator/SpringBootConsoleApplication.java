package com.melody.generator;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.lang.System.exit;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

    public static void main(String[] args) throws Exception {
        SpringApplication app = new SpringApplication(SpringBootConsoleApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

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

        LSTM lstm = new LSTM();
        lstm.run(keyAndChords);
        exit(0);
    }
}