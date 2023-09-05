package com.klimjavadev.todolist.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.klimjavadev.todolist.models.Task;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TaskResponse {
    private Long id;
    private String name;
    private String priority;
    private String state;

    public TaskResponse(Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.priority = task.getPriority().name();
        this.state = task.getState().getName();
    }
}
