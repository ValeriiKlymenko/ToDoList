package com.klimjavadev.todolist.models.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.klimjavadev.todolist.models.entity.ToDo;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ToDoRequest {
    String title;
    LocalDateTime createdAt;
    UserResponse owner;

    List<UserResponse> collaborators;

    public ToDoRequest(ToDo todo) {
        title = todo.getTitle();
        createdAt = todo.getCreatedAt();
        owner = new UserResponse(todo.getOwner());
        collaborators = todo.getCollaborators().stream().map(UserResponse::new).collect(Collectors.toList());
    }
}
