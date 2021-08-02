package com.example.service.interfaces;

import com.example.dto.ActionsToPlayer;
import com.example.model.GameRoom;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface GameplayService {
    void sendLike(ActionsToPlayer actionsToPlayer) throws JsonProcessingException;

    void sendGoGame(GameRoom gameRoom) throws JsonProcessingException;

    void sendPlayerExited(GameRoom gameRoom) throws JsonProcessingException;

    void sendDeletePlayer(ActionsToPlayer actionsToPlayer) throws JsonProcessingException;
}
