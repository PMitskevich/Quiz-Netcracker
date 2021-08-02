package com.example.controller;

import com.example.dto.GameCategoryDto;
import com.example.model.GameCategory;
import com.example.service.interfaces.GameCategoryService;
import com.example.service.mapper.GameCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/gameCategory")
public class GameCategoryController {

    private final GameCategoryService gameCategoryService;
    private final GameCategoryMapper mapper;

    @Autowired
    public GameCategoryController(GameCategoryService gameCategoryService, GameCategoryMapper mapper) {
        this.gameCategoryService = gameCategoryService;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<GameCategoryDto> getGameCategories() {
        return gameCategoryService.findAllGameCategory()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

//    @GetMapping("/{gameCategoryId}")
//    public GameCategoryDto getGames(@PathVariable UUID gameCategoryId) {
//        return mapper.toDto(gameCategoryService.findGameCategoryById(gameCategoryId));
//    }

    @PostMapping("/save")
    public GameCategoryDto createGameCategory(@RequestBody GameCategoryDto gameCategoryDto) {
        GameCategory gameCategory = mapper.toEntity(gameCategoryDto);
        return mapper.toDto(gameCategoryService.saveGameCategory(gameCategory));
    }

    @PutMapping("/update/{gameCategoryId}")
    public GameCategoryDto updateGameCategory(@PathVariable UUID gameCategoryId,
                                              @RequestBody GameCategoryDto gameCategoryDto) {
        GameCategory gameCategory = mapper.toEntity(gameCategoryDto);
        return mapper.toDto(gameCategoryService.updateGameCategory(gameCategoryId, gameCategory));
    }

    @DeleteMapping("/delete/{gameCategoryId}")
    public ResponseEntity<?> deleteGameCategory(@PathVariable UUID gameCategoryId) {
        gameCategoryService.deleteGameCategory(gameCategoryId);
        return ResponseEntity.ok().build();
    }
}

