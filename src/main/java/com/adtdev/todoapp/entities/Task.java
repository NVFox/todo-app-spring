package com.adtdev.todoapp.entities;

import com.adtdev.todoapp.enums.TaskStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String title;

    @Column
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;
}
