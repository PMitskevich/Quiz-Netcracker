package com.example.model;

import com.example.dto.UserDto;
import lombok.Data;

import java.util.List;

@Data
public class UserPage {
    private int pages;
    private List<UserDto> users;
}
