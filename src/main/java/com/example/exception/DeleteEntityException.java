package com.example.exception;

import com.example.exception.detail.ErrorInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DeleteEntityException extends QuizBaseException {
    public DeleteEntityException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
