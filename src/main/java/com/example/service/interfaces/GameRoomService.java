package com.example.service.interfaces;

import com.example.dto.GameRoomDto;
import com.example.model.GameRoom;

import java.util.Optional;
import java.util.UUID;

public interface GameRoomService {
    GameRoom findById(UUID id);

    GameRoom save(GameRoom gameRoom);

    GameRoom findGameRoom(UUID gameId, UUID playerId);

    void delete(UUID id);

    Optional<GameRoomDto> getOptionalGameRoom(UUID gameRoomId, UUID playerId);
}
