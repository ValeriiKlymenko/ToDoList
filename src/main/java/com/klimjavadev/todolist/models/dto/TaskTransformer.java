package com.klimjavadev.todolist.models.dto;

import com.klimjavadev.todolist.models.entity.Priority;
import com.klimjavadev.todolist.models.entity.State;
import com.klimjavadev.todolist.models.entity.Task;
import com.klimjavadev.todolist.models.entity.ToDo;

public class TaskTransformer {
    public static TaskDto convertToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getName(),
                task.getPriority().toString(),
                task.getTodo().getId(),
                task.getState().getId()
        );
    }

    public static Task convertToEntity(TaskDto taskDto, ToDo todo, State state) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setPriority(Priority.valueOf(taskDto.getPriority()));
        task.setTodo(todo);
        task.setState(state);
        return task;
    }
}
