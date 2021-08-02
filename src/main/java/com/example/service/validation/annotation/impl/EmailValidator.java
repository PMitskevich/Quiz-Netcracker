package com.example.service.validation.annotation.impl;

import com.example.service.validation.annotation.EmailConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {
    private Pattern emailRegexp;

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
        emailRegexp = Pattern.compile(constraintAnnotation.regexp(), Pattern.CASE_INSENSITIVE);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if (email == null) {
            return false;
        }
        Matcher matcher = emailRegexp.matcher(email);
        return matcher.find();
    }
}
