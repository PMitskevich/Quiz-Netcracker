package com.example.service.impl;

import com.example.model.Game;
import com.example.repository.GamePageRepository;
import com.example.service.interfaces.GamePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GamePageServiceImpl implements GamePageService {

    private final GamePageRepository gamePageRepository;

    @Autowired
    public GamePageServiceImpl(GamePageRepository gamePageRepository) {
        this.gamePageRepository = gamePageRepository;
    }

    @Override
    public Page<Game> findAll(PageRequest pageRequest) {
        return gamePageRepository.findAll(pageRequest);
    }
}
