package com.eazybytes.accounts.exception;

import com.eazybytes.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * extends ResponseEntityExceptionHandler because we are adding field validation and override handleMethodArgumentNotValid
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationError = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach( error -> {
            String fieldName = ((FieldError)error).getField();
            String validationMsg = error.getDefaultMessage();
            validationError.put(fieldName,validationMsg);
        });
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    /**-- Handling all the exception logic --- */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleAllTheException(Exception excp, WebRequest webRequest){
        ErrorResponseDto errorReponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                excp.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorReponseDto);
    }

    @ExceptionHandler(CustomerAlreadyExitsException.class)
    public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExitsException(CustomerAlreadyExitsException customerAlreadyExitsException, WebRequest webRequest){
        ErrorResponseDto errorReponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                customerAlreadyExitsException.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorReponseDto,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,WebRequest webRequest){
        ErrorResponseDto errorReponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                resourceNotFoundException.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorReponseDto);
    }
}
