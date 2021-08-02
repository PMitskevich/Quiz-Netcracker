package com.example.model;

import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@Table(name = "questions")
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id")
    private UUID id;

    @NotBlank(message = "message.QuestionTitleNotValid", groups = {Create.class, Update.class} )
    @Column(name = "title")
    private String title;

    @NotBlank(message = "message.QuestionDescriptionNotValid", groups = {Create.class, Update.class} )
    @Column(name = "description")
    private String description;

    @EqualsAndHashCode.Exclude
    @NotNull(message = "message.CategoryNotValid", groups = {Create.class, Update.class})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull(message = "message.LevelNotValid", groups = {Create.class, Update.class})
    @JoinColumn(name = "levelId")
    private Level level;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @NotNull(message = "message.GameIdNotValid", groups = {Update.class})
    @ManyToOne
    private Game game;

    @Valid
    @OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Set<Answer> answersSet;

    @Column(name = "photo")
    private String photo;
}
