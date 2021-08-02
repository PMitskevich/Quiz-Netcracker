package com.example.service.mapper;

import com.example.dto.AnswerDto;
import com.example.model.Answer;
import com.example.model.Question;
import com.example.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapper implements Mapper<Answer, AnswerDto> {
    private final QuestionService questionService;

    @Autowired
    public AnswerMapper(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public AnswerDto toDto(Answer entity) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(entity.getId());
        answerDto.setTitle(entity.getTitle());
        answerDto.setRight(entity.getRight());
        answerDto.setQuestion(entity.getQuestion().getId());
        return answerDto;
    }

    @Override
    public Answer toEntity(AnswerDto dto) {
        Question answerQuestion = null;
        if (dto.getQuestion() != null) {
            answerQuestion = questionService.getQuestionById(dto.getQuestion());
        }
        Answer answer = new Answer();
        answer.setId(dto.getId());
        answer.setTitle(dto.getTitle());
        answer.setRight(dto.getRight());
        answer.setQuestion(answerQuestion);
        return answer;
    }

    @Override
    public AnswerDto toShortDto(Answer entity) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(entity.getId());
        answerDto.setTitle(entity.getTitle());
        answerDto.setQuestion(entity.getQuestion().getId());
        return answerDto;
    }
}
