package com.victor.HelpDesk.config;

import com.victor.HelpDesk.serveces.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TesteConfig {
    @Autowired
    private DBService dbService;

    @Bean
    public void instanciaDB(){

        this.dbService.InstanciaDB();
    }

}
