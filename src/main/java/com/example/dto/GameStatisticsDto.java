package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GameStatisticsDto {
    private QuestionDto question;
    private AnswerDto answer;
    private double percent;

    public GameStatisticsDto(QuestionDto question, AnswerDto answer, double percent) {
        this.question = question;
        this.answer = answer;
        this.percent = percent;
    }
}
