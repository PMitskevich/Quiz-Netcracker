package com.example.exception.detail;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public enum ErrorInfo {

    ARGUMENT_NOT_VALID("title.ArgumentNotValid", "0001", HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND("title.ResourceNotFound", "0002", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("title.InternalServerError", "0003", HttpStatus.INTERNAL_SERVER_ERROR),
    DELETE_ENTITY_ERROR("title.DeleteEntityError", "0004", HttpStatus.NOT_FOUND),
    AUTHORIZATION_ERROR("title.AuthorizationError", "0005", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_ERROR("title.InvalidEmail", "0006", HttpStatus.BAD_REQUEST),
    INVALID_ACTIVATION_CODE_ERROR("title.InvalidActivationCode", "0007", HttpStatus.BAD_REQUEST),
    NON_EXISTING_MAIL_ERROR("title.NonExistingMailError", "0008", HttpStatus.BAD_REQUEST),
    EXISTING_USER_ERROR("title.ExistingUserError", "0009", HttpStatus.BAD_REQUEST),
    INVALID_USER_ACTIVATION_ERROR("title.InvalidUserActivationError", "0010", HttpStatus.BAD_REQUEST),
    INVALID_GAME_ACCESS_ERROR("title.InvalidGameAccessError", "0011", HttpStatus.NOT_FOUND);

    private final String errorTitle;
    private final String errorCode;
    private final HttpStatus httpStatus;

    ErrorInfo(String errorTitle, String errorCode, HttpStatus httpStatus) {
        this.errorTitle = errorTitle;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}
