package com.example.service.interfaces;

import com.example.model.Level;

import java.util.List;
import java.util.UUID;

public interface LevelService {
    List<Level> findAll();

    Level findLevelById(UUID id);

    Level save(Level level);

    Level update(UUID id, Level level);

    void delete(UUID id);
}
