package com.example.service.mapper;

import com.example.dto.GameCategoryDto;
import com.example.model.GameCategory;
import com.example.service.interfaces.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameCategoryMapper implements Mapper<GameCategory, GameCategoryDto> {
    private final GameService gameService;

    @Autowired
    public GameCategoryMapper(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public GameCategoryDto toDto(GameCategory entity) {
        GameCategoryDto gameCategoryDto = new GameCategoryDto();
        gameCategoryDto.setId(entity.getId());
        gameCategoryDto.setTitle(entity.getTitle());
        gameCategoryDto.setDescription(entity.getDescription());
        return gameCategoryDto;
    }

    @Override
    public GameCategory toEntity(GameCategoryDto dto) {
        GameCategory gameCategory = new GameCategory();
        gameCategory.setId(dto.getId());
        gameCategory.setTitle(dto.getTitle());
        gameCategory.setDescription(dto.getDescription());
        return gameCategory;
    }

    @Override
    public GameCategoryDto toShortDto(GameCategory entity) {
        GameCategoryDto gameCategoryDto = new GameCategoryDto();
        gameCategoryDto.setId(entity.getId());
        gameCategoryDto.setTitle(entity.getTitle());
        gameCategoryDto.setDescription(entity.getDescription());
        return gameCategoryDto;
    }
}

