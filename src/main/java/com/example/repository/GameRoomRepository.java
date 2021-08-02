package com.example.repository;

import com.example.model.GameRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameRoomRepository extends JpaRepository<GameRoom, UUID> {
    List<GameRoom> findGameRoomByGameId(UUID gameId);

    GameRoom findGameRoomById(UUID id);

    void deleteByGameId(UUID id);
}
