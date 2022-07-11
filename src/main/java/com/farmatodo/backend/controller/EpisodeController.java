package com.farmatodo.backend.controller;

import com.farmatodo.backend.services.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/v1/episode")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping(path = "/{param}")

    public ResponseEntity<Map<String, Object>> getEpisode(@PathVariable("param") String param) {
        return episodeService.getEpisode(param);
    }
}
