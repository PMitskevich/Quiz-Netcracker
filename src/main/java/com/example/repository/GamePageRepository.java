package com.example.repository;

import com.example.model.Game;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GamePageRepository extends PagingAndSortingRepository<Game, UUID> {
}
