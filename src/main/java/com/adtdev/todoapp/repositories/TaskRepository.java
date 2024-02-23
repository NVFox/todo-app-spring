package com.adtdev.todoapp.repositories;

import com.adtdev.todoapp.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
