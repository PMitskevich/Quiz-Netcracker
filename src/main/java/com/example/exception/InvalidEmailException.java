package com.example.exception;

import com.example.exception.detail.ErrorInfo;

public class InvalidEmailException extends QuizBaseException {
    public InvalidEmailException(ErrorInfo errorInfo, String message) {
        super(errorInfo, message);
    }
}
