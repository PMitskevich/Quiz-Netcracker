package com.example.model;

import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "message.GameTitleNotValid", groups = {Create.class, Update.class})
    @Size(min = 4, max = 24)
    @Column(name = "name")
    private String title;

    @NotBlank(message = "message.GameDescriptionNotValid", groups = {Create.class, Update.class})
    @Column(name = "description")
    private String description;

    @Column(name = "views")
    private Long views;

    @Column(name = "rating")
    private Long averageRating;

    @Column(name = "ratingCount")
    private Long ratingCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gameCategoryId")
    private GameCategory gameCategory;

    @NotNull(message = "message.PlayerIdNotValid", groups = {Create.class, Update.class})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playerId")
    private Player player;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    private Set<Question> questions;

    @Column(name = "photo")
    private String photo;

    @NotNull(message = "message.AccessNotValid", groups = {Create.class, Update.class})
    @Column(name = "access")
    private String access;
}