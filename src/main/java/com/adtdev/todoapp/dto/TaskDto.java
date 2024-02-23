package com.adtdev.todoapp.dto;

import com.adtdev.todoapp.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record TaskDto(
    String title,
    String description,
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dueDate,
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    TaskStatus status
) {}
