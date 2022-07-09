package com.farmatodo.backend.services;

import com.farmatodo.backend.entity.Episode;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface EpisodeServiceInterface {
    ResponseEntity<Map<String, Object>> getEpisode(int Id);
}
