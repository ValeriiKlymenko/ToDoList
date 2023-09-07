package com.klimjavadev.todolist.controllers;

import com.klimjavadev.todolist.models.dto.*;
import com.klimjavadev.todolist.models.entity.Task;
import com.klimjavadev.todolist.models.entity.User;
import com.klimjavadev.todolist.services.RoleService;
import com.klimjavadev.todolist.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
        logger.info("Creating user");
        User user = UserTransformer.convertToEntity(
                userDto,
                roleService.readById(userDto.getRoleId())
                );
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> readUser(@PathVariable long id) {
        logger.info("Read user with id = {}", id);
        User user = userService.readById(id);
        UserDto userDto = UserTransformer.convertToDto(user);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id,
                                        @Valid @RequestBody UserDto userDto) {
        logger.info("Updating user with id = {}", id);
        User user = UserTransformer.convertToEntity(
                userDto,
                roleService.readById(userDto.getRoleId())
        );
        userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) {
        logger.info("Delete user with id = {}", id);
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    List<UserResponse> getAllUsers() {
        logger.info("Getting all users");
        return userService.getAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}
