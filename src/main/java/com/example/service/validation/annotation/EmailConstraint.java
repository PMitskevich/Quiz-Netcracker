package com.example.service.validation.annotation;

import com.example.service.validation.annotation.impl.AnswersSetValidator;
import com.example.service.validation.annotation.impl.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {
    String message() default "message.InvalidEmail";
    String regexp() default "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
