package com.example.exception;

import com.example.exception.detail.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuthorizationException extends QuizBaseException {
    public AuthorizationException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
