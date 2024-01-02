package sfu.rkis7.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import sfu.rkis7.model.Watch;
import sfu.rkis7.repositories.WatchRepository;

import java.util.Optional;

/**
 * Класс для тестирования запросов для REST контроллера
 */
@Component
public class RestClient {

    private final WatchRepository watchRepository;

    private static final String BASE_URL = "http://localhost:8080/rest/watch";

    @Autowired
    public RestClient(WatchRepository watchRepository) {
        this.watchRepository = watchRepository;
    }

    public void testGetAllWatch() {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL, String.class);
            printResponse("GET All Watch", response);
        } catch (HttpClientErrorException exception) {
            printNotFound("GET All Watch", exception.getMessage());
        }
    }

    public void testGetFilterAllWatchByWeight(int weight) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL + "?weight=" + weight, String.class);
            printResponse("GET Filter Watch", response);
        } catch (HttpClientErrorException exception) {
            printNotFound("GET Filter Watch", exception.getMessage());
        }
    }

    public void testGetWatchById(int id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL + "/" + id, String.class);
            printResponse("GET Watch by ID", response);
        } catch (HttpClientErrorException exception) {
            printNotFound("GET Watch by ID", exception.getMessage());
        }
    }

    public void testAddWatch() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Watch newWatch = new Watch("Mercer", "Mechanic", 10000.0, 3, 275);
        HttpEntity<Watch> requestEntity = new HttpEntity<>(newWatch, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(BASE_URL, requestEntity, String.class);

        printResponse("POST Add Watch", response);
    }

    public void testUpdateWatch(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Watch watch;
        Optional<Watch> updatedWatch = watchRepository.findById((Integer) id);
        if (updatedWatch.isPresent()) {
            watch = updatedWatch.get();
            watch.setBrand("SomeNew1");
        } else {
            watch = new Watch();
        }
        HttpEntity<Watch> requestEntity = new HttpEntity<>(watch, headers);
        try {
            ResponseEntity<String> response = new RestTemplate().exchange(
                    BASE_URL + "/" + id, HttpMethod.PUT, requestEntity, String.class);
            printResponse("PUT Update Watch", response);
        } catch (HttpClientErrorException exception) {
            printNotFound("PUT Update Watch", exception.getMessage());
        }
    }

    public void testDeleteWatch(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);
        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(BASE_URL + "/" + id, HttpMethod.DELETE, requestEntity, String.class);
            printResponse("DELETE Watch", response);
        } catch (HttpClientErrorException exception) {
            printNotFound("DELETE Watch", exception.getMessage());
        }
    }

    public static void printResponse(String testName, ResponseEntity<String> response) {
        System.out.println("--------------------");
        System.out.println("Test: " + testName);
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody());
        System.out.println();
    }

    public static void printNotFound(String testName, String status) {
        System.out.println("--------------------");
        System.out.println("Test: " + testName);
        System.out.println("Status: " + status);
        System.out.println();
    }
}
