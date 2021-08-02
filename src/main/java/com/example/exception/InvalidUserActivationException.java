package com.example.exception;

import com.example.exception.detail.ErrorInfo;

public class InvalidUserActivationException extends QuizBaseException {
    public InvalidUserActivationException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
