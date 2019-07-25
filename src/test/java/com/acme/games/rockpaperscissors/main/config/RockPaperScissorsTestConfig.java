package com.acme.games.rockpaperscissors.main.config;

import com.acme.games.rockpaperscissors.main.service.GameService;
import com.acme.games.rockpaperscissors.main.service.RockPaperScissorsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RockPaperScissorsTestConfig {

    @Bean
    public GameService gameService() {
        return new RockPaperScissorsService();
    }
}
