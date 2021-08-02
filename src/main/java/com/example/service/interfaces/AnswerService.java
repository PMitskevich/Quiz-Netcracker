package com.example.service.interfaces;

import com.example.model.Answer;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.UUID;

public interface AnswerService {
    Answer createAnswer(Answer answer);

    void deleteAnswer(UUID id);

    Answer updateAnswer(UUID id, Answer answer);

    Answer getAnswerById(UUID id);

    List<Answer> getALL();

    Answer saveStatisticsAndReturnAnswer(UUID questionId, String answerId, UUID playerId, UUID gameRoomId, int numberAnswer) throws JsonProcessingException;
}
