package com.example.exception;

import com.example.exception.detail.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends QuizBaseException {

    public ResourceNotFoundException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
