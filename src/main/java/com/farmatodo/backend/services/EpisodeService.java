package com.farmatodo.backend.services;

import com.farmatodo.backend.entity.Episode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class EpisodeService {

    private final static Logger logger = LoggerFactory.getLogger(EpisodeService.class);

    @Value("${url.episodio}")
    private String url_episodio;

    public ResponseEntity<Map<String, Object>> getEpisode(int Id) {
        try {
            final String uri = url_episodio + Id;
            RestTemplate restTemplate = new RestTemplate();
            Episode result = restTemplate.getForObject(uri, Episode.class);
            return ResponseEntity.status(HttpStatus.OK).body(result != null ? result.toJson() : null);
        } catch (Exception exception) {
            logger.error(exception.getMessage());
            return ResponseEntity.status(HttpStatus.FOUND).body(Map.of("error", exception.getMessage()));
        }
    }
}
