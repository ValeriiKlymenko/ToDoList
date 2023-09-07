package com.klimjavadev.todolist.services.impl;

import com.klimjavadev.todolist.exceptions.NullEntityReferenceException;
import jakarta.persistence.EntityNotFoundException;
import com.klimjavadev.todolist.models.entity.Task;
import com.klimjavadev.todolist.repositories.TaskRepository;
import com.klimjavadev.todolist.services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {
        if (task != null) {
            return taskRepository.save(task);
        }
        throw new NullEntityReferenceException("Task cannot be 'null'");
    }

    @Override
    public Task readById(long id) {

        EntityNotFoundException exception = new EntityNotFoundException("Task with id " + id + " not found");
        return taskRepository.findById(id).orElseThrow(
                () -> exception);
    }

    @Override
    public Task update(Task task) {
        if (task != null) {
            readById(task.getId());
            return taskRepository.save(task);
        }
        throw new NullEntityReferenceException("Task cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        Task task = readById(id);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        return taskRepository.getByTodoId(todoId);
    }
}
