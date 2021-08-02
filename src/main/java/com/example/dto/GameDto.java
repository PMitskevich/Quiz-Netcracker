package com.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GameDto {
    UUID id;
    String title;
    String description;
    Set<QuestionDto> questions;
    UUID player;
    String photo;

//    UUID gameCategory;
    Long averageRating;
    Long views;
    Long ratingCount;
    String access;
}
