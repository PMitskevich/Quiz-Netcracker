package com.example.service.mapper;

import com.example.dto.GameRoomDto;
import com.example.model.GameRoom;
import com.example.service.interfaces.GameService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GameRoomMapper implements Mapper<GameRoom, GameRoomDto> {
    private final PlayerMapper playerMapper;
    private final GameService gameService;

    public GameRoomMapper(PlayerMapper playerMapper, GameService gameService) {
        this.playerMapper = playerMapper;
        this.gameService = gameService;
    }


    @Override
    public GameRoomDto toDto(GameRoom entity) {
        GameRoomDto dto = new GameRoomDto();
        dto.setId(entity.getId());
        dto.setGame(entity.getGame().getId());
        dto.setPlayers(entity.getPlayers().stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public GameRoom toEntity(GameRoomDto dto) {
        GameRoom entity = new GameRoom();
        entity.setGame(gameService.findGameById(dto.getGame()));
        entity.setPlayers(dto.getPlayers().stream()
                .map(playerMapper::toEntity)
                .collect(Collectors.toSet()));
        return entity;
    }

    @Override
    public GameRoomDto toShortDto(GameRoom entity) {
        return null;
    }
}
