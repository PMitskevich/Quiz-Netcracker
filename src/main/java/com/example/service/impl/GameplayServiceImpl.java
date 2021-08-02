package com.example.service.impl;

import com.example.dto.ActionsToPlayer;
import com.example.model.GameRoom;
import com.example.model.Player;
import com.example.service.interfaces.GameRoomService;
import com.example.service.interfaces.GameplayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class GameplayServiceImpl implements GameplayService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final GameRoomService gameRoomService;

    public GameplayServiceImpl(SimpMessagingTemplate simpMessagingTemplate, GameRoomService gameRoomService) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.gameRoomService = gameRoomService;
    }

    @Override
    public void sendLike(ActionsToPlayer actionsToPlayer) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (Player player : gameRoomService.findById(actionsToPlayer.getGameRoomId()).getPlayers()) {
            if (actionsToPlayer.getRecipientId().equals(player.getId())) {
                simpMessagingTemplate.convertAndSend("/topic/game/" + actionsToPlayer.getRecipientId(), objectMapper.writeValueAsString(actionsToPlayer.getName()));
            }
        }
    }

    @Override
    public void sendGoGame(GameRoom gameRoom) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (Player player : gameRoom.getPlayers()) {
            simpMessagingTemplate.convertAndSend("/topic/game/" + player.getId(), objectMapper.writeValueAsString("go"));
        }
    }

    @Override
    public void sendPlayerExited(GameRoom gameRoom) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        for (Player player : gameRoom.getPlayers()) {
            simpMessagingTemplate.convertAndSend("/topic/game/" + player.getId(), mapper.writeValueAsString(gameRoom.getPlayers()));
        }
    }

    @Override
    public void sendDeletePlayer(ActionsToPlayer actionsToPlayer) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (Player player : gameRoomService.findById(actionsToPlayer.getGameRoomId()).getPlayers()) {
            if (actionsToPlayer.getRecipientId().equals(player.getId())) {
                simpMessagingTemplate.convertAndSend("/topic/game/" + actionsToPlayer.getRecipientId(), objectMapper.writeValueAsString("delete"));
            }
        }
    }
}
