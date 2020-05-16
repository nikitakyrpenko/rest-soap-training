package com.micka.exception.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {

    private HttpStatus httpStatus;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    private String message;

    private String exceptionMessage;

    private ApiError(){
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus httpStatus, Throwable e){
        this();
        this.httpStatus = httpStatus;
        this.message = "Unexpected error";
        this.exceptionMessage = e.getLocalizedMessage();
    }

    public ApiError(HttpStatus httpStatus,String message, Throwable e){
        this();
        this.httpStatus = httpStatus;
        this.message = message;
        this.exceptionMessage = e.getLocalizedMessage();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
