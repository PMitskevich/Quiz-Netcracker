package com.example.exception;

import com.example.exception.detail.ErrorInfo;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Map;

@Getter
public abstract class QuizBaseException extends RuntimeException {
    private final ErrorInfo errorInfo;
    private Map<String, String> propertyViolation;
    private MessageSource messageSource;

    public QuizBaseException(ErrorInfo errorInfo, String message) {
        super(message);
        this.errorInfo = errorInfo;
    }

    public QuizBaseException(ErrorInfo errorInfo, Map<String, String> propertyViolation, MessageSource messageSource) {
        this.errorInfo = errorInfo;
        this.propertyViolation = propertyViolation;
        this.messageSource = messageSource;
    }

    public String getMessage() {
        if (propertyViolation != null) {
            StringBuilder message = new StringBuilder();
            for (Map.Entry<String, String> entry: propertyViolation.entrySet()) {
                message.append(messageSource.getMessage(entry.getValue(), null, LocaleContextHolder.getLocale())).append("; ");
            }
            return String.valueOf(message);
        }
        else {
            return super.getMessage();
        }
    }
}
