package com.example.demo.application.target_part.common.exceptionHandler.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;
import java.util.Arrays;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private int code;
    private String message;
    private String exception;
    private Instant timestamp;
    private String trace;

    public ErrorResponse(Exception exception, int code, boolean traceEnabled) {
        this.code = code;
        this.message = exception.getMessage();
        this.exception = exception.getClass().getName();
        this.timestamp = Instant.now();
        if (traceEnabled) {
            this.trace = Arrays.toString(exception.getStackTrace());
        }
    }
}
