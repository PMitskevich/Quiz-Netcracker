package com.example.repository;

import com.example.model.GameAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameAccessRepository extends JpaRepository<GameAccess, UUID> {
    GameAccess findGameAccessById(UUID id);

    GameAccess findGameAccessesByGameIdAndPlayerId(UUID gameId, UUID playerId);

    List<GameAccess> findGameAccessesByPlayerId(UUID playerId);

    List<GameAccess> findGameAccessesByGameId(UUID id);

    GameAccess findGameAccessesByActivationCode(String code);
}
