package com.example.QuizNetcrackerbackend;

import com.example.dto.GameDto;
import com.example.service.validation.group.Create;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertFalse;

public class GameDtoValidationTest {
    private Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void invalidTitleAndDescriptionShouldFailValidation() {
        GameDto gameDto = new GameDto();
        gameDto.setTitle(null);
        gameDto.setDescription(null);
        gameDto.setQuestions(new HashSet<>());
        gameDto.setPlayer(UUID.fromString("0d588698-a71e-43a1-beb8-ae995ae8d366"));
        gameDto.setPhoto(null);
        gameDto.setAccess("PUBLIC");
        Set<ConstraintViolation<GameDto>> violations = validator.validate(gameDto, Create.class);
        assertFalse(violations.isEmpty());
    }
}
