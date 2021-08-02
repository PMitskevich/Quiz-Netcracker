package com.example.dto;

import com.example.model.GameCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameFilter {

    private String descripton;
    private String title;
    private Long views;
    private Long averageRating;
    private Long ratingCount;
    private GameCategory gameCategory;

}
