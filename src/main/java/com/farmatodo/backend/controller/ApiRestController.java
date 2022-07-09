package com.farmatodo.backend.controller;

import com.farmatodo.backend.services.EpisodeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ApiRestController {

    private EpisodeServiceInterface episodeService;

    public ApiRestController(EpisodeServiceInterface episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping(path = "/v1/episode/{param}")

    public ResponseEntity<Map<String, Object>> getEpisode(@PathVariable("param") String param) {
        return episodeService.getEpisode(Integer.parseInt(param));
    }
}
