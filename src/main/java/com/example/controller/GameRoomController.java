package com.example.controller;


import com.example.dto.GameRoomDto;
import com.example.service.interfaces.GameRoomService;
import com.example.service.mapper.GameRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/game-room")
public class GameRoomController {
    private final GameRoomMapper gameRoomMapper;
    private final GameRoomService gameRoomService;

    @Autowired
    public GameRoomController(GameRoomMapper gameRoomMapper, GameRoomService gameRoomService) {
        this.gameRoomMapper = gameRoomMapper;
        this.gameRoomService = gameRoomService;
    }

    @PostMapping("/save")
    public GameRoomDto save(@RequestBody GameRoomDto gameRoomDto) {
        return gameRoomMapper.toDto(gameRoomService.save(gameRoomMapper.toEntity(gameRoomDto)));
    }

    @GetMapping("/{gameId}/{playerId}")
    public GameRoomDto findGameRoom(@PathVariable UUID gameId, @PathVariable UUID playerId) {
        return gameRoomMapper.toDto(gameRoomService.findGameRoom(gameId, playerId));
    }

    @GetMapping("/{id}")
    public GameRoomDto findGameRoomById(@PathVariable UUID id) {
        return gameRoomMapper.toDto(gameRoomService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        gameRoomService.delete(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{gameRoomId}/{playerId}")
    public Optional<GameRoomDto> deletePlayer(@PathVariable UUID gameRoomId, @PathVariable UUID playerId) {
        return gameRoomService.getOptionalGameRoom(gameRoomId, playerId);
    }
}
