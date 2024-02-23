package com.adtdev.todoapp.mappers;

import com.adtdev.todoapp.dto.CreateTaskDto;
import com.adtdev.todoapp.dto.TaskDto;
import com.adtdev.todoapp.dto.UpdateTaskDto;
import com.adtdev.todoapp.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDto taskToDto(Task task);

    List<TaskDto> taskListToDtoList(List<Task> taskList);

    default Page<TaskDto> taskPageToDtoPage(Page<Task> taskPage) {
        return taskPage.map(this::taskToDto);
    }
    @Mapping(target = "id", ignore = true)
    Task createTaskDtoToTask(CreateTaskDto dto);

    @Mapping(target = "id", ignore = true)
    Task updateTaskWithDto(UpdateTaskDto dto, @MappingTarget Task task);
}
