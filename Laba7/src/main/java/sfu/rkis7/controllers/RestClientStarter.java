package sfu.rkis7.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * A class for running query testing for a REST controller
 */
@Component
public class RestClientStarter implements CommandLineRunner {

    private static RestClient restClient;
    private static final Logger logger = LoggerFactory.getLogger(RestClientStarter.class);

    @Autowired
    public RestClientStarter(RestClient restClient) {
        RestClientStarter.restClient = restClient;
    }

    @Override
    public void run(String... args) {
        try {
            restClient.testAddWatch();
            restClient.testAddWatch();
            restClient.testGetAllWatch();
            restClient.testGetFilterAllWatchByWeight(275);
            restClient.testGetWatchById(1);
            restClient.testUpdateWatch(1);
            restClient.testDeleteWatch(1);
            restClient.testDeleteWatch(2);
        } catch (Exception exception){
            logger.error("Error executing REST requests: {}", exception.getMessage());
        }
    }
}
