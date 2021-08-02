package com.example.controller;

import com.example.dto.AnswerDto;
import com.example.model.Answer;
import com.example.service.interfaces.AnswerService;
import com.example.service.mapper.AnswerMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/answer")
@CrossOrigin(origins = {"http://localhost:4200"})
public class AnswerController {
    private final AnswerService answerService;
    private final AnswerMapper mapper;

    @Autowired
    public AnswerController(AnswerService answerService, AnswerMapper mapper) {
        this.answerService = answerService;
        this.mapper = mapper;
    }

    @GetMapping("/{questionId}/{answerId}/{playerId}/{gameRoomId}/{numberAnswer}")
    public AnswerDto getAnswer(@PathVariable UUID questionId, @PathVariable String answerId, @PathVariable UUID playerId,
                               @PathVariable UUID gameRoomId, @PathVariable int numberAnswer) throws JsonProcessingException {
        Answer answer = answerService.saveStatisticsAndReturnAnswer(questionId, answerId, playerId, gameRoomId, numberAnswer);
        if (answer == null) {
            return null;
        }
        return mapper.toDto(answer);
    }

    @GetMapping("/{id}")
    public AnswerDto getAnswer(@PathVariable UUID id) {
        return mapper.toDto(answerService.getAnswerById(id));
    }

    @GetMapping("/all")
    public List<AnswerDto> getAnswer() {
        return answerService.getALL().stream()
                .map(mapper::toShortDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/answer")
    public AnswerDto createAnswer(@RequestBody AnswerDto answerDto) {
        Answer answer = mapper.toEntity(answerDto);
        return mapper.toDto(answerService.createAnswer(answer));
    }

    @PutMapping("/{id}")
    public AnswerDto updateAnswer(@PathVariable UUID id,
                                  @RequestBody AnswerDto answerDto) {
        Answer answer = mapper.toEntity(answerDto);
        return mapper.toDto(answerService.updateAnswer(id, answer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLevel(@PathVariable UUID id) {
        answerService.deleteAnswer(id);
        return ResponseEntity.ok().build();
    }
}
