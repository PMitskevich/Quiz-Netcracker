package com.example.dto;

import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AnswerDto {
    UUID id;
    String title;
    Boolean right;
    UUID question;
}


