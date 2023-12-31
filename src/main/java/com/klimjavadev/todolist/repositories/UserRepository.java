package com.klimjavadev.todolist.repositories;

import com.klimjavadev.todolist.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
