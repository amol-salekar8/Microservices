package com.eazybytes.accounts.exception;


import com.eazybytes.accounts.dto.ErrorReponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerAlreadyExitsException.class)
    public ResponseEntity<ErrorReponseDto> handleCustomerAlreadyExitsException(CustomerAlreadyExitsException customerAlreadyExitsException, WebRequest webRequest){
        ErrorReponseDto errorReponseDto = new ErrorReponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                customerAlreadyExitsException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorReponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorReponseDto> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,WebRequest webRequest){
        ErrorReponseDto errorReponseDto = new ErrorReponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                resourceNotFoundException.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReponseDto);
    }
}
