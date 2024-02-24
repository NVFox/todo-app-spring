package com.adtdev.todoapp.dto;

import com.adtdev.todoapp.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CreateTaskDto(
        @NotEmpty @Size(min = 3, max = 150)
        String title,
        @Size(min = 3, max = 255)
        String description,
        @JsonFormat(pattern = "dd-MM-yyyy")
        @FutureOrPresent
        LocalDate dueDate,
        @JsonFormat(shape = JsonFormat.Shape.STRING)
        @NotNull
        TaskStatus status
) {}
