package com.example.service.interfaces;

import com.example.model.Question;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    List<Question> findAllQuestion();

    Question saveQuestion(Question question);

    Question updateQuestion(UUID id, Question questionRequest);

    void deleteQuestion(UUID id);

    Question getQuestionById(UUID id);

    List<Question> getQuestionsByGameId(UUID id);

    void setQuestionToAnswers(Question question);

    Question getQuestionByTitle(String title);
}
