package com.klimjavadev.todolist.models.dto;

import com.klimjavadev.todolist.models.entity.*;

public class ToDoTransformer {
    public static ToDoDto convertToDto(ToDo todo) {
        return new ToDoDto(
                todo.getId(),
                todo.getTitle(),
                todo.getCreatedAt(),
                todo.getOwner().getId()
        );
    }

    public static ToDo convertToEntity(ToDoDto toDoDto, User user) {
        ToDo todo = new ToDo();
        todo.setId(toDoDto.getId());
        todo.setTitle(toDoDto.getTitle());
        todo.setCreatedAt(toDoDto.getCreatedAt());
        todo.setOwner(user);
        return todo;
    }
}
