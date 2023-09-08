package com.klimjavadev.todolist.controllers;

import com.klimjavadev.todolist.models.dto.*;
import com.klimjavadev.todolist.models.entity.ToDo;
import com.klimjavadev.todolist.services.ToDoService;
import com.klimjavadev.todolist.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@RequestMapping("/api/users/{user-id}/todos")
public class ToDoController {

    private Logger logger = LoggerFactory.getLogger(ToDoController.class);
    private final ToDoService toDoService;
    private final UserService userService;

    @Autowired
    public ToDoController(ToDoService toDoService, UserService userService) {
        this.toDoService = toDoService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createToDo(@PathVariable("user-id") Long userId,
                                        @Valid @RequestBody ToDoDto toDoDto) {
        logger.info("Create todo with user-id = {}", userId);
        ToDo toDo = ToDoTransformer.convertToEntity(
                toDoDto,
                userService.readById(toDoDto.getOwner())
        );
        toDoService.create(toDo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{todo-id}")
    public ResponseEntity<ToDoDto> readToDo(@PathVariable("todo-id") long todoId,
                                            @PathVariable("user-id") long userId) {
        logger.info("Read todo with id = {}", todoId);
//        checkForExistenceTodoAndOwner(userId, todoId);
        ToDo toDo = toDoService.readById(todoId);
        ToDoDto toDoDto = ToDoTransformer.convertToDto(toDo);
        return ResponseEntity.ok(toDoDto);
    }

    @PutMapping("/{todo-id}")
    public ResponseEntity<?> updateToDo(@PathVariable("todo-id") long todoId,
                                        @PathVariable("user-id") long userId,
                                        @Valid @RequestBody ToDoDto toDoDto) {
        logger.info("Update todo with id = {}", todoId);
//        checkForExistenceTodoAndOwner(userId, todoId);
        ToDo toDo = ToDoTransformer.convertToEntity(
                toDoDto,
                userService.readById(toDoDto.getOwner())
        );
        toDoService.update(toDo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{todo-id}")
    public ResponseEntity<?> deleteToDo(@PathVariable("todo-id") long todoId,
                                        @PathVariable("user-id") long userId) {
        logger.info("Delete todo with id = {}", todoId);
//        checkForExistenceTodoAndOwner(userId, todoId);
        toDoService.delete(todoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ToDoDto>> getAllToDo(@PathVariable("user-id") long userId) {
        logger.info("Get all todo with user-id = {}", userId);
        try {
            userService.readById(userId);//existsUserById()
        } catch (IndexOutOfBoundsException exception) {
            throw new EntityNotFoundException(String.format("User with id %d not found, userId"));
        }
        List<ToDo> toDos = toDoService.getByUserId(userId);
        List<ToDoDto> toDoDtoList = toDos.stream()
                .map(ToDoTransformer::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(toDoDtoList);
    }

}
