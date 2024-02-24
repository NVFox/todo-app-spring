package com.adtdev.todoapp.controllers;

import com.adtdev.todoapp.dto.CreateTaskDto;
import com.adtdev.todoapp.dto.PageRequestDto;
import com.adtdev.todoapp.dto.TaskDto;
import com.adtdev.todoapp.dto.UpdateTaskDto;
import com.adtdev.todoapp.services.ITaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "tasks")
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService taskService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Iterable<TaskDto> getAllTasks(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer perPage
    ) {
        if (page != null || perPage != null) {
            return taskService.getTasksInPage(new PageRequestDto(page, perPage));
        }

        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDto getTask(@PathVariable Long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto createTask(@Valid @RequestBody CreateTaskDto dto) {
        return taskService.createTask(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDto updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskDto dto) {
        return taskService.updateTask(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
