package com.farmatodo.backend.repository;

import com.farmatodo.backend.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Integer> {
}