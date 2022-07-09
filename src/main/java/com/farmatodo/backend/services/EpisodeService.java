package com.farmatodo.backend.services;

import com.farmatodo.backend.entity.Episode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class EpisodeService implements EpisodeServiceInterface {

    private final static Logger logger = LoggerFactory.getLogger(EpisodeService.class);

    @Override
    public ResponseEntity<Map<String, Object>> getEpisode(int Id) {
        try {
            final String uri = "https://rickandmortyapi.com/api/episode/" + Id;
            RestTemplate restTemplate = new RestTemplate();
            Episode result = restTemplate.getForObject(uri, Episode.class);
            return ResponseEntity.status(HttpStatus.OK).body(result.toJson());
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("error", exception.getMessage()));
        }


    }
}
