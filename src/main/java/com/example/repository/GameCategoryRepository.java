package com.example.repository;

import com.example.model.GameCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameCategoryRepository extends JpaRepository<GameCategory, UUID> {
    GameCategory getGameCategoryById(UUID id);
}
