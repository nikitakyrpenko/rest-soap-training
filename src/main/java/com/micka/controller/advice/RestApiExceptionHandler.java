package com.micka.controller.advice;

import com.micka.exception.RecordNotFoundException;
import com.micka.exception.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler{

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ApiError> handleRecordNotFound(RecordNotFoundException e){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e);
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e);
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }
}
