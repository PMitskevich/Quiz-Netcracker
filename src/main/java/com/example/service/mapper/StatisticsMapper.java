package com.example.service.mapper;

import com.example.dto.StatisticsDto;
import com.example.model.Statistics;
import com.example.service.interfaces.AnswerService;
import com.example.service.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatisticsMapper implements Mapper<Statistics, StatisticsDto> {

    private final AnswerService answerService;
    private final PlayerService playerService;

    @Autowired
    public StatisticsMapper(AnswerService answerService, PlayerService playerService) {
        this.answerService = answerService;
        this.playerService = playerService;
    }

    @Override
    public StatisticsDto toDto(Statistics entity) {
        if (entity == null) {
            return null;
        }
        StatisticsDto dto = new StatisticsDto();
        dto.setId(entity.getId());
        dto.setAnswer(entity.getAnswer().getId());
        dto.setPlayer(entity.getPlayer().getId());
        return dto;
    }

    @Override
    public Statistics toEntity(StatisticsDto dto) {
        Statistics entity = new Statistics();
        entity.setId(dto.getId());
        entity.setAnswer(answerService.getAnswerById(dto.getAnswer()));
        entity.setPlayer(playerService.findPlayerByUserId(dto.getPlayer()));
        return entity;
    }


    @Override
    public StatisticsDto toShortDto(Statistics entity) {
        return null;
    }
}
