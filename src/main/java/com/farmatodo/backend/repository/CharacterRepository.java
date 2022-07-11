package com.farmatodo.backend.repository;

import com.farmatodo.backend.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, Integer> {
}