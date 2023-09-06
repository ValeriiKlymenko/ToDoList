package com.klimjavadev.todolist.repositories;

import com.klimjavadev.todolist.models.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("from Task where todo.id = :todoId")
    List<Task> getByTodoId(long todoId);

    @Query("from Task where todo.id = :todoId")
    List<Task> getTasksByTodoId(long todoId);
}
