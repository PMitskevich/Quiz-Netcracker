package com.example.service.mapper;

import com.example.dto.GameAccessDto;
import com.example.dto.StatisticsDto;
import com.example.model.GameAccess;
import com.example.model.Statistics;
import com.example.service.interfaces.AnswerService;
import com.example.service.interfaces.GameService;
import com.example.service.interfaces.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GameAccessMapper implements Mapper<GameAccess, GameAccessDto> {

    private final GameService gameService;
    private final PlayerService playerService;

    @Autowired
    public GameAccessMapper(GameService gameService,  PlayerService playerService) {
        this.gameService = gameService;
        this.playerService = playerService;
    }

    @Override
    public GameAccessDto toDto(GameAccess entity) {
        if(entity == null){
            return null;
        }
        GameAccessDto dto = new GameAccessDto();
        dto.setId(entity.getId());
        dto.setAccess(entity.isAccess());
        dto.setGame(entity.getGame().getId());
        dto.setPlayer(entity.getPlayer().getId());
        return dto;
    }

    @Override
    public GameAccess toEntity(GameAccessDto dto) {
        GameAccess entity = new GameAccess();
        entity.setId(dto.getId());
        entity.setGame(gameService.findGameById(dto.getGame()));
        entity.setPlayer(playerService.findPlayerByUserId(dto.getPlayer()));
        return entity;
    }


    @Override
    public GameAccessDto toShortDto(GameAccess entity) {
        return null;
    }
}
