package com.example.repository;

import com.example.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, UUID> {
    List<Statistics> getStatisticsByPlayerId(UUID id);

    List<Statistics> getStatisticsByQuestionId(UUID id);

    Statistics getStatisticsByQuestionIdAndPlayerId(UUID questionId, UUID playerId);

    void deleteByQuestionId(UUID id);
}
