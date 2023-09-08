package com.klimjavadev.todolist.models.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.klimjavadev.todolist.models.entity.Task;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TaskRequest {
    private String name;
    private String priority;
    private String state;

    public TaskRequest(Task task) {
        this.name = task.getName();
        this.priority = task.getPriority().name();
        this.state = task.getState().getName();
    }
}
