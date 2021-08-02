package com.example.exception.detail;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
public class ExceptionDetails {
    private Date timestamp;
    private String errorTitle;
    private String errorCode;
    private String message;
}
