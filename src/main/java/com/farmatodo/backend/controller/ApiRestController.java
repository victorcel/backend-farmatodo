package com.farmatodo.backend.controller;

import com.farmatodo.backend.entity.Episode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@RestController
public class ApiRestController {

    @GetMapping(path = "/v1/episode/{param}")
    public ResponseEntity<Map<String, Object>> getEpisode(@PathVariable("param") String param) {
        try {
            final String uri = "https://rickandmortyapi.com/api/episode/" + param;
            RestTemplate restTemplate = new RestTemplate();
            Episode result = restTemplate.getForObject(uri, Episode.class);
            return ResponseEntity.status(HttpStatus.OK).body(result.toJson());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("error", exception.getMessage()));
        }


    }
}
