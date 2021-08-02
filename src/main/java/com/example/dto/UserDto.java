package com.example.dto;

import com.example.service.validation.group.Update;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class UserDto {
    UUID id;
    String email;
    String password;
    String login;
    String role;
    UUID player;
    boolean active;
    String activationCode;
    boolean isActive;
}
