package com.example.service.interfaces;

import com.example.dto.GameDto;
import com.example.dto.GameStatisticsDto;
import com.example.model.Statistics;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public interface StatisticsService {
    List<Statistics> findStatisticsByPlayerId(UUID id);

    List<GameStatisticsDto> findStatisticsByPlayerIdAndGameId(UUID gameId, UUID playerId);

    Statistics save(Statistics statistics);

    void delete(UUID id, UUID gameId);

    Map<String, Double> getTotalPercentAllPlayers();

    double getTotalPercentByPlayerId(UUID uuid);

    Set<GameDto> findGameByPlayerIdAndGameId(UUID playerId);
}
