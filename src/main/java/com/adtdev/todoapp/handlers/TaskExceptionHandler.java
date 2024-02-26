package com.adtdev.todoapp.handlers;

import com.adtdev.todoapp.dto.errors.ValidationErrorDto;
import com.adtdev.todoapp.dto.errors.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@RestControllerAdvice
public class TaskExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<ValidationErrorDto> validationExceptionHandler(MethodArgumentNotValidException e) {
        var errors = new HashMap<String, List<String>>();

        for (var fieldError : e.getFieldErrors()) {
            errors.computeIfAbsent(fieldError.getField(), x -> new ArrayList<>())
                    .add(fieldError.getDefaultMessage());
        }

        var validationErrors = new ArrayList<ValidationErrorDto>();

        for (var entry : errors.entrySet()) {
            String key = entry.getKey();
            var value = entry.getValue().stream().map(
                    val -> val.substring(0, 1).toUpperCase() + val.substring(1)
            ).toList();

            validationErrors.add(new ValidationErrorDto(key, value));
        }

        return new ErrorResponse<>(
                HttpStatus.BAD_REQUEST,
                "Validation errors have been found. (" + e.getFieldErrorCount() + " detected)",
                "Validation Error",
                validationErrors
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse<String> incorrectParamTypeExceptionHandler(MethodArgumentTypeMismatchException e) {
        return new ErrorResponse<>(
                HttpStatus.BAD_REQUEST,
                "Incorrect Param Type",
                "Argument Error",
                List.of("Use the correct type for " + Objects.requireNonNull(e.getPropertyName()))
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse<String> noSuchElementExceptionHandler(NoSuchElementException e) {
        return new ErrorResponse<>(
                HttpStatus.NOT_FOUND,
                "Element Not Found",
                "NotFound Error",
                List.of("Element has not been found")
        );
    }
}
