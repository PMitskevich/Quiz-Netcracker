package com.example.exception;

import com.example.exception.detail.ErrorInfo;

public class NonExistingMailException extends QuizBaseException {
    public NonExistingMailException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
