package com.klimjavadev.todolist.repositories;

import com.klimjavadev.todolist.models.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
