package com.klimjavadev.todolist.controllers;

import com.klimjavadev.todolist.dto.UserResponse;
import com.klimjavadev.todolist.models.User;
import com.klimjavadev.todolist.services.ToDoService;
import com.klimjavadev.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
    UserService userService;
    ToDoService toDoService;

    @Autowired
    public UserController(UserService userService, ToDoService toDoService) {
        this.userService = userService;
        this.toDoService = toDoService;
    }

    //201
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        userService.create(user);
        return userService.readById(user.getId());
    }

    //ok
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> readUser(@PathVariable long id) {
        User user = userService.readById(id);
        return ResponseEntity.ok(new UserResponse(user));
    }

    //200 204
    //потрібно dto
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable long id,
                                                   @Valid @RequestBody User user) {
        User oldUser = userService.readById(id);
        user.setRole(oldUser.getRole());
        userService.update(user);
        return ResponseEntity.ok(new UserResponse(user));
    }

    //якщо немає юзера - 404, якщо видалили то 204
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping
    List<UserResponse> getAllUsers() {
        return userService.getAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }
}
