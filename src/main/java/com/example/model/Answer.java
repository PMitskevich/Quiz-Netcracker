package com.example.model;

import com.example.service.validation.group.Create;
import com.example.service.validation.group.Update;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Data
@Table(name = "answers")
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    @Id
    @GeneratedValue
    @Type(type = "pg-uuid")
    @Column(name = "id")
    private UUID id;

    @NotEmpty(message = "message.AnswerTitleNotValid", groups = {Create.class, Update.class})
    @Column(name = "title")
    private String title;

    @NotNull(message = "message.AnswerRightNotValid", groups = {Create.class, Update.class})
    @Column(name = "answerIsRight")
    private Boolean right;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    private Question question;
}
