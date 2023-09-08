package com.klimjavadev.todolist.repositories;

import com.klimjavadev.todolist.models.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    @Query("from ToDo where owner.id= :userId")
    List<ToDo> getByUserId(long userId);
}
