package com.example.controller;

import com.example.dto.ActionsToPlayer;
import com.example.dto.GameRoomDto;
import com.example.model.GameRoom;
import com.example.service.interfaces.GameplayService;
import com.example.service.mapper.GameRoomMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class GameplayController {

    private final GameRoomMapper mapper;
    private final GameplayService gameplayService;

    public GameplayController(GameRoomMapper mapper, GameplayService gameplayService) {
        this.mapper = mapper;
        this.gameplayService = gameplayService;
    }

    @MessageMapping("/like-player")
    public void sendLike(@Payload ActionsToPlayer actionsToPlayer) throws JsonProcessingException {
        gameplayService.sendLike(actionsToPlayer);
    }

    @MessageMapping("/go-game")
    public void sendGoGame(@Payload GameRoomDto gameRoomDto) throws JsonProcessingException {
        GameRoom gameRoom = mapper.toEntity(gameRoomDto);
        gameplayService.sendGoGame(gameRoom);
    }

    @MessageMapping("/game-room/close")
    public void sendPlayerExited(@Payload GameRoomDto gameRoomDto) throws JsonProcessingException {
        GameRoom gameRoom = mapper.toEntity(gameRoomDto);
        gameplayService.sendPlayerExited(gameRoom);
    }

    @MessageMapping("/delete-player")
    public void sendDeletePlayer(@Payload ActionsToPlayer actionsToPlayer) throws JsonProcessingException {
        gameplayService.sendDeletePlayer(actionsToPlayer);
    }
}
