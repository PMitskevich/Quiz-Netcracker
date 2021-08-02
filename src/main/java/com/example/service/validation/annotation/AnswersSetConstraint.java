package com.example.service.validation.annotation;

import com.example.service.validation.annotation.impl.AnswersSetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = AnswersSetValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface AnswersSetConstraint {
    String message() default "message.DuplicateAnswer";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
