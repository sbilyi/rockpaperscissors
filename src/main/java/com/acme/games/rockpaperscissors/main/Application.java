package com.acme.games.rockpaperscissors.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // TODO user move prediction system
    // TODO persistence level need to be added
    // TODO RockPaperSCissorsService tests as well
    // TODO Round needs to have Id or something for us to identify it
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
