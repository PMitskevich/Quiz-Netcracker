package com.example.repository;

import com.example.model.Game;
import com.example.model.QGame;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GameRepository extends ExCustomRepository<Game, QGame, UUID> {
    //public interface GameRepository extends JpaRepository<Game, UUID> {
    Game findGameById(UUID id);

    Game findGameByTitle(String title);

    //Поиск по имени
    List<Game> findAllByTitleContaining(String title);

    //Фильтр по категории
    List<Game> findAllByGameCategoryId(UUID id);

    //Сортировка
    List<Game> findByOrderByViewsDesc();
    // List<Game> findByOrderByViewsDesc();

    List<Game> findByOrderByTitleAsc();

    List<Game> findTop3ByOrderByViewsDesc();

    List<Game> findAllByOrderByAverageRating();

//    //Dynamic Filter
    //  List<Game> findByFilter(GameFilter filter);


    //querydsl


    List<Game> findGamesByPlayerId(UUID playerId);

    List<Game> findGameByAccess(String access);
}