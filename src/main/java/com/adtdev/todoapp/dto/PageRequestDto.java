package com.adtdev.todoapp.dto;

import jakarta.validation.constraints.Min;

public record PageRequestDto(
        @Min(value = 1)
        Integer page,
        @Min(value = 1)
        Integer elements
) {
    public PageRequestDto(Integer page, Integer elements) {
        this.page = page != null ? page : 1;
        this.elements = elements != null ? elements : 3;
    }

    public Integer page() {
        return this.page - 1;
    }
}
