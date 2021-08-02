package com.example.controller;

import com.example.dto.LevelDto;
import com.example.model.Level;
import com.example.service.interfaces.LevelService;
import com.example.service.mapper.LevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/level")
public class LevelController {
    private final LevelService levelService;
    private final LevelMapper mapper;

    @Autowired
    public LevelController(LevelService levelService, LevelMapper mapper) {
        this.levelService = levelService;
        this.mapper = mapper;
    }

    @GetMapping()
    public List<LevelDto> getLevels() {
        return levelService.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{levelId}")
    public LevelDto getLevel(@PathVariable UUID levelId) {
        return mapper.toDto(levelService.findLevelById(levelId));
    }

    @PostMapping("save")
    public LevelDto createLevel(@Valid @RequestBody LevelDto levelDto) {
        Level level = mapper.toEntity(levelDto);
        return mapper.toDto(levelService.save(level));
    }

    @PutMapping("/update/{levelId}")
    public LevelDto updateLevel(@PathVariable UUID levelId,
                                @Valid @RequestBody LevelDto levelDto) {
        Level level = mapper.toEntity(levelDto);
        return mapper.toDto(levelService.update(levelId, level));
    }

    @DeleteMapping("/delete/{levelId}")
    public ResponseEntity<?> deleteLevel(@PathVariable UUID levelId) {
        levelService.delete(levelId);
        return ResponseEntity.ok().build();
    }
}
