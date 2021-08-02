package com.example.model;

import com.example.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUser {
    private List<UserDto> users;
    private int totalPages;
    private int pageNumber;
    private int pageSize;
}
