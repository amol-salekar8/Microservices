package com.eazybytes.accounts.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorReponseDto {

    private String apiPath;

    private HttpStatus errorCode;

    private String errorMsg;

    private LocalDateTime errorTime;
}
