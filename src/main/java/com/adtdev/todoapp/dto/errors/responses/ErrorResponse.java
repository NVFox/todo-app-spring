package com.adtdev.todoapp.dto.errors.responses;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse<T>(
        Integer status,
        String type,
        String message,
        List<T> errors
) {
    public ErrorResponse(HttpStatus status, String message, String type, List<T> errors) {
        this(status.value(), type, message, errors);
    }
}
