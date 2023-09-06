package com.klimjavadev.todolist.controllers;

import com.klimjavadev.todolist.models.entity.User;
import com.klimjavadev.todolist.services.ToDoService;
import com.klimjavadev.todolist.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    UserService userService;
    ToDoService toDoService;

    @Autowired
    public UserController(UserService userService, ToDoService toDoService) {
        this.userService = userService;
        this.toDoService = toDoService;
    }
//
//    //201
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        logger.info("Creating user with id = {}", user.getId());
        userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//
//    //ok
//    @GetMapping("/{id}")
//    public ResponseEntity<UserResponse> readUser(@PathVariable long id) {
//        logger.info("Read user with id = {}", id);
//        User user = userService.readById(id);
//        return ResponseEntity.ok(new UserResponse(user));
//    }
//
//    //200 204
//    //потрібно dto
//    @PatchMapping("/{id}")
//    public ResponseEntity<UserResponse> updateUser(@PathVariable long id,
//                                                   @Valid @RequestBody User user) {
//        logger.info("Updating user with id = {}", id);
//        User oldUser = userService.readById(id);
//        user.setRole(oldUser.getRole());
//        userService.update(user);
//        return ResponseEntity.ok(new UserResponse(user));
//    }
//
//    //якщо немає юзера - 404, якщо видалили то 204
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteUser(@PathVariable long id) {
//        logger.info("Delete user with id = {}", id);
//        userService.delete(id);
//        return ResponseEntity.ok("User deleted successfully");
//    }
//
//    @GetMapping
//    List<UserResponse> getAllUsers() {
//        logger.info("Getting all users");
//        return userService.getAll().stream()
//                .map(UserResponse::new)
//                .collect(Collectors.toList());
//    }
}
