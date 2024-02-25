package com.example.demo.application.target_part.common.exceptionHandler;

import com.example.demo.application.target_part.common.exceptionHandler.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

    @Value("${server.response.error.trace.enabled:true}")
    private boolean traceEnabled;

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception ex) {
        return new ResponseEntity<>(new ErrorResponse(ex, HttpStatus.BAD_REQUEST.value(), traceEnabled), HttpStatus.BAD_REQUEST);
    }
}
