package com.example.service.interfaces;

import com.example.model.GameCategory;

import java.util.List;
import java.util.UUID;

public interface GameCategoryService {
    GameCategory findGameCategoryById(UUID id);

    List<GameCategory> findAllGameCategory();

    GameCategory saveGameCategory(GameCategory category);

    GameCategory updateGameCategory(UUID categoryId, GameCategory categoryRequest);

    void deleteGameCategory(UUID categoryId);
}
