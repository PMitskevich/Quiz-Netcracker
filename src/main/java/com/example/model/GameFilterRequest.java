package com.example.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameFilterRequest {
    private String title;
    private String description;
    private Long views;
    private Long averageRating;
    private Long ratingCount;
    private GameCategory gameCategory;
}
