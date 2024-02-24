package com.adtdev.todoapp.dto.errors;

import java.util.List;

public record ValidationErrorDto(
        String field,
        List<String> messages
) {}
