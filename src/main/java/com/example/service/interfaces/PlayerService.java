package com.example.service.interfaces;

import com.example.model.Player;

import java.util.List;
import java.util.UUID;

public interface PlayerService {
    Player updatePlayer(UUID playerId, Player playerRequest);

    Player savePlayer(Player player);

    List<Player> findAllPlayers();

    Player findPlayerById(UUID id);

    Player findPlayerByUserId(UUID player);

    Player findGuest(String name);

    void deletePlayer(UUID id);
}
