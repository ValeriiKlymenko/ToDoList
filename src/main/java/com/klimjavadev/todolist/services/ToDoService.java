package com.klimjavadev.todolist.services;

import com.klimjavadev.todolist.models.entity.ToDo;
import com.klimjavadev.todolist.models.entity.User;

import java.util.List;

public interface ToDoService {
    ToDo create(ToDo todo);
    ToDo readById(long id);
    ToDo update(ToDo todo);
    void delete(long id);
    List<ToDo> getAll();

    List<ToDo> getByUserId(long userId);

    public ToDo addCollaborator (User user, ToDo toDo);

    public ToDo deleteCollaborator (User user, ToDo toDo);
}
