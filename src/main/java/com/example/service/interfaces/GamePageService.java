package com.example.service.interfaces;

import com.example.model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface GamePageService {
    Page<Game> findAll(PageRequest pageRequest);
}
