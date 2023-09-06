package com.klimjavadev.todolist.services.impl;

import com.klimjavadev.todolist.exceptions.NullEntityReferenceException;
import com.klimjavadev.todolist.models.entity.ToDo;
import com.klimjavadev.todolist.models.entity.User;
import com.klimjavadev.todolist.repositories.ToDoRepository;
import com.klimjavadev.todolist.services.ToDoService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository todoRepository;

    public ToDoServiceImpl(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo create(ToDo todo) {
        if (todo != null) {
            return todoRepository.save(todo);
        }
        throw new NullEntityReferenceException("ToDo cannot be 'null'");
    }


    @Override
    public ToDo readById(long id) {
        return todoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("ToDo with id " + id + " not found"));
    }

    @Override
    public ToDo update(ToDo todo) {
        if (todo != null) {
            readById(todo.getId());
            return todoRepository.save(todo);
        }
        throw new NullEntityReferenceException("ToDo cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        ToDo todo = readById(id);
        todoRepository.delete(todo);
    }

    @Override
    public List<ToDo> getAll() {
        return todoRepository.findAll();
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        return todoRepository.getByUserId(userId);
    }

    @Override
    public ToDo addCollaborator (User user, ToDo toDo){
        List<User> collaborators = toDo.getCollaborators();
        if (collaborators.contains(user)){
            throw new EntityExistsException("Collaborator already exists.");
        }
        collaborators.add(user);
        toDo.setCollaborators(collaborators);
        return this.update(toDo);
    }

    @Override
    public ToDo deleteCollaborator (User user, ToDo toDo){
        List<User> collaborators = toDo.getCollaborators();
        if(collaborators.contains(user)){
            collaborators.remove(user);
            toDo.setCollaborators(collaborators);
            return update(toDo);
        } else {
            throw new EntityNotFoundException("User are not collaborator");
        }
    }
}
