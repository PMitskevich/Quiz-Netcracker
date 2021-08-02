package com.example.exception;

import com.example.exception.detail.ErrorInfo;

public class ExistingUserException extends QuizBaseException {
    public ExistingUserException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
