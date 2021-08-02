package com.example.exception;

import com.example.exception.detail.ErrorInfo;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ArgumentNotValidException extends QuizBaseException {
    public ArgumentNotValidException(ErrorInfo errorInfo, Map<String, String> propertyViolation, MessageSource messageSource) {
        super(errorInfo, propertyViolation, messageSource);
    }
}
