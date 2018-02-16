package com.example.Project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class HerokuConfig {

    @Bean
    public HerokuNotIdle herokuNotIdle(){
        return new HerokuNotIdle();
    }
}
