package com.example.exception.handler;

import com.example.exception.AuthorizationException;
import com.example.exception.QuizBaseException;
import com.example.exception.detail.ErrorInfo;
import com.example.exception.detail.ExceptionDetails;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    private final MessageSource messageSource;

    public ExceptionController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(QuizBaseException.class)
    public ResponseEntity<?> quizBaseExceptionHandling(QuizBaseException exception) {
        String errorTitle = exception.getErrorInfo().getErrorTitle();
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setErrorTitle(messageSource.getMessage(errorTitle, null, LocaleContextHolder.getLocale()));
        exceptionDetails.setTimestamp(new Date());
        exceptionDetails.setErrorCode(exception.getErrorInfo().getErrorCode());
        exceptionDetails.setMessage(exception.getMessage());
        HttpStatus httpStatus = exception.getErrorInfo().getHttpStatus();
        return new ResponseEntity<>(exceptionDetails, httpStatus);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> runtimeExceptionHandler(Exception exception) {
//        if (exception instanceof AuthorizationException) {
//            quizBaseExceptionHandling((AuthorizationException) exception);
//        }
//        String errorTitle = ErrorInfo.INTERNAL_SERVER_ERROR.getErrorTitle();
//        ExceptionDetails exceptionDetails = new ExceptionDetails();
//        exceptionDetails.setErrorTitle(messageSource.getMessage(errorTitle, null, LocaleContextHolder.getLocale()));
//        exceptionDetails.setTimestamp(new Date());
//        exceptionDetails.setErrorCode(ErrorInfo.INTERNAL_SERVER_ERROR.getErrorCode());
//        exceptionDetails.setMessage(messageSource.getMessage("message.InternalServerError", null, LocaleContextHolder.getLocale()));
//        HttpStatus httpStatus = ErrorInfo.INTERNAL_SERVER_ERROR.getHttpStatus();
//        return new ResponseEntity<>(exceptionDetails, httpStatus);
//    }
}
