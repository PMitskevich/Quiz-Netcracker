package com.example.exception;

import com.example.exception.detail.ErrorInfo;

public class InvalidGameAccessException extends QuizBaseException {
    public InvalidGameAccessException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
