package com.example.Project.config;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

public class HerokuNotIdle {

    private static final Logger LOG = LoggerFactory.getLogger(HerokuNotIdle.class);

    // Fire to Heroku every 10 minutes to keep connection alive
    @Scheduled(fixedDelay = 600000)
    public void herokuNotIdle(){
        LOG.debug("Heroku not idle execution");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://sports-performance.herokuapp.com", String.class);
    }
}
