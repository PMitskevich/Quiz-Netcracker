package com.example.repository;

import com.example.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> getQuestionByCategoryId(UUID id);

    Question getQuestionById(UUID id);

    List<Question> getQuestionByGameId(UUID id);

    Question findQuestionByTitle(String title);
}
