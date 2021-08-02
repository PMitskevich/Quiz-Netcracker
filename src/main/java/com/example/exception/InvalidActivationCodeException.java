package com.example.exception;

import com.example.exception.detail.ErrorInfo;

public class InvalidActivationCodeException extends QuizBaseException {
    public InvalidActivationCodeException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
