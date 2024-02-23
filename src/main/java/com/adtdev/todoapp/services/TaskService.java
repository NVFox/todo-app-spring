package com.adtdev.todoapp.services;

import com.adtdev.todoapp.dto.CreateTaskDto;
import com.adtdev.todoapp.dto.PageRequestDto;
import com.adtdev.todoapp.dto.TaskDto;
import com.adtdev.todoapp.dto.UpdateTaskDto;
import com.adtdev.todoapp.entities.Task;
import com.adtdev.todoapp.repositories.TaskRepository;
import com.adtdev.todoapp.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService implements ITaskService {
    private TaskRepository taskRepository;

    private TaskMapper taskMapper;

    @Override
    public List<TaskDto> getAllTasks() {
        return taskMapper.taskListToDtoList(taskRepository.findAll());
    }

    @Override
    public Page<TaskDto> getTasksInPage(PageRequestDto request) {
        return taskMapper.taskPageToDtoPage(
                taskRepository.findAll(PageRequest.of(request.page(), request.elements()))
        );
    }

    @Override
    public TaskDto getTask(Long id) {
        return taskMapper.taskToDto(
                taskRepository.findById(id).orElseThrow()
        );
    }

    @Override
    public TaskDto createTask(CreateTaskDto task) {
        return taskMapper.taskToDto(
                taskRepository.save(taskMapper.createTaskDtoToTask(task))
        );
    }

    @Override
    public TaskDto updateTask(Long id, UpdateTaskDto task) {
        Task storedTask = taskRepository.findById(id)
                .orElseThrow();

        return taskMapper.taskToDto(
                taskRepository.save(taskMapper.updateTaskWithDto(task, storedTask))
        );
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
