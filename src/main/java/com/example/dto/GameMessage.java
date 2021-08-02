package com.example.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GameMessage {
    private int numberAnswer;
    private boolean right;
    private UUID playerId;

    public GameMessage(boolean right, UUID playerId, int numberAnswer) {
        this.numberAnswer = numberAnswer;
        this.right = right;
        this.playerId = playerId;
    }
}
