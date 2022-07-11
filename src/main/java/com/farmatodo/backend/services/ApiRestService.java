package com.farmatodo.backend.services;

import com.farmatodo.backend.entity.Episode;
import com.farmatodo.backend.entity.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiRestService {

    private final static Logger logger = LoggerFactory.getLogger(ApiRestService.class);

    private static final String URL_HOST = "https://rickandmortyapi.com";
    private static final String URL_EPISODE = "/api/episode/";
    private static final String URL_CHARACTER = "/api/character/";
    private static final String URL_LOCATION = "/api/location/";

    private RestTemplate restTemplate;

    public Episode getEpisode(Long id) throws Exception {
        String url = URL_HOST + URL_EPISODE + id;
        return restTemplate.getForObject(url, Episode.class);
    }

    public List<Character> getCharacters(String characters) throws Exception {
        String url = URL_HOST + URL_CHARACTER + characters;

        ResponseEntity<List<Character>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Character>>() {
                }
        );

        return responseEntity.getBody();
    }

    public List<Location> getLocation(String locations) throws Exception {
        String url = URL_HOST + URL_LOCATION + locations;

        ResponseEntity<List<Location>> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Location>>() {
                }
        );

        return responseEntity.getBody();
    }
}
