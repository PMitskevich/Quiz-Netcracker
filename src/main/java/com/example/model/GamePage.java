package com.example.model;

import com.example.dto.GameDto;
import lombok.Data;
import java.util.List;

@Data
public class GamePage {
    private int pages;
    private List<GameDto> games;
}
