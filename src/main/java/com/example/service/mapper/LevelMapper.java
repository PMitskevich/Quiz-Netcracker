package com.example.service.mapper;

import com.example.dto.LevelDto;
import com.example.model.Level;
import org.springframework.stereotype.Component;

@Component
public class LevelMapper implements Mapper<Level, LevelDto> {

    @Override
    public LevelDto toDto(Level entity) {
        LevelDto levelDto = new LevelDto();
        levelDto.setId(entity.getId());
        levelDto.setDescription(entity.getDescription());
        levelDto.setTitle(entity.getTitle());
        return levelDto;
    }

    @Override
    public Level toEntity(LevelDto dto) {
        Level level = new Level();
        level.setId(dto.getId());
        level.setDescription(dto.getDescription());
        level.setTitle(dto.getTitle());
        return level;
    }

    @Override
    public LevelDto toShortDto(Level entity) {
        LevelDto levelDto = new LevelDto();
        levelDto.setDescription(entity.getDescription());
        levelDto.setTitle(entity.getTitle());
        return levelDto;
    }
}
