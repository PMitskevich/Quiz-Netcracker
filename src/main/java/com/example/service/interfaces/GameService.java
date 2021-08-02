package com.example.service.interfaces;

import com.example.model.Game;
import com.example.model.GameFilterRequest;

import java.util.List;
import java.util.UUID;

public interface GameService {
    Game createGame(Game game);

    void deleteGame(UUID gameId, UUID playerId);

    Game updateGame(UUID id, Game game);

    Game findGameByTitle(String name);

    Game findGameById(UUID id);

    List<Game> findAllGames();

    List<Game> searchGamesByTitle(String title);

    List<Game> findGamesByCategory(UUID gameCategoryId);

    List<Game> findAllGamesFilteredByTitle();

    List<Game> findAllGamesFilteredByRating();

    List<Game> findAllGamesFilteredByViews();

    List<Game> findTopViewedGames();

    List<Game> findByFilter(GameFilterRequest request);

    //Game updateGameViews();
    //Game updateGameRating(Long rate);

    List<Game> findGameByPlayerId(UUID playerId);

    List<Game> findPublicGames();
}
