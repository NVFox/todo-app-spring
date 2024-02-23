package com.adtdev.todoapp.services;

import com.adtdev.todoapp.dto.CreateTaskDto;
import com.adtdev.todoapp.dto.PageRequestDto;
import com.adtdev.todoapp.dto.TaskDto;
import com.adtdev.todoapp.dto.UpdateTaskDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ITaskService {
    public List<TaskDto> getAllTasks();

    public Page<TaskDto> getTasksInPage(PageRequestDto request);

    public TaskDto getTask(Long id);

    public TaskDto createTask(CreateTaskDto task);

    public TaskDto updateTask(Long id, UpdateTaskDto task);

    public void deleteTask(Long id);
}
