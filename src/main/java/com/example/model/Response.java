package com.example.model;

import com.example.dto.GameDto;
import lombok.AllArgsConstructor;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response {
    private int totalPages;
    private int pageNumber;
    private int pageSize;
}
