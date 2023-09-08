package com.klimjavadev.todolist.controllers;

import com.klimjavadev.todolist.models.dto.TaskDto;
import com.klimjavadev.todolist.models.dto.TaskTransformer;
import com.klimjavadev.todolist.models.entity.Task;
import com.klimjavadev.todolist.services.StateService;
import com.klimjavadev.todolist.services.TaskService;
import com.klimjavadev.todolist.services.ToDoService;
import jakarta.persistence.EntityNotFoundException;
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
@RequestMapping("/api/todos/{todo-id}/tasks")
public class TaskController {
    private Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final ToDoService toDoService;
    private final TaskService taskService;
    private final StateService stateService;

    @Autowired
    public TaskController(ToDoService toDoService, TaskService taskService, StateService stateService) {
        this.toDoService = toDoService;
        this.taskService = taskService;
        this.stateService = stateService;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@PathVariable("todo-id") Long todoId,
                                    @Valid @RequestBody TaskDto taskDto) {
        logger.info("Create task with todo-id = {}", todoId);
        //Логіку перенести в Service
//        try {
//            toDoService.readById(todoId);
//        } catch (IndexOutOfBoundsException exception) {
//            throw new EntityNotFoundException(String.format("ToDo with id %d not found, todoId"));
//        }
        Task task = TaskTransformer.convertToEntity(
                taskDto,
                toDoService.readById(todoId),
                stateService.readById(taskDto.getStateId())
        );
        taskService.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{task-id}")
    public ResponseEntity<TaskDto> readTask(@PathVariable("task-id") long taskId,
                                           @PathVariable("todo-id") long todoId) {
        logger.info("Read task with id = {}", taskId);
//        checkForExistenceTodoAndTask(taskId, todoId);
        Task task = taskService.readById(taskId);
        TaskDto taskDto = TaskTransformer.convertToDto(task);
        return ResponseEntity.ok(taskDto);
    }

    @PutMapping("/{task-id}")
    public ResponseEntity<?> updateTask(@PathVariable("task-id") long taskId,
                                    @PathVariable("todo-id") long todoId,
                                    @Valid @RequestBody TaskDto taskDto) {
        logger.info("Update task with id = {}", taskId);
//        checkForExistenceTodoAndTask(taskId, todoId);
        Task task = TaskTransformer.convertToEntity(
                taskDto,
                toDoService.readById(todoId),
                stateService.readById(taskDto.getStateId())
        );
        taskService.update(task);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{task-id}")
    public ResponseEntity<?> deleteTask(@PathVariable("task-id") long taskId,
                                    @PathVariable("todo-id") long todoId) {
        logger.info("Delete task with id = {}", taskId);
//        checkForExistenceTodoAndTask(taskId, todoId);
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable("todo-id") long todoId) {
        logger.info("Get all tasks with todo-id = {}", todoId);
        try {
            toDoService.readById(todoId);//existsTaskById()
        } catch (IndexOutOfBoundsException exception) {
            throw new EntityNotFoundException(String.format("ToDo with id %d not found, todoId"));
        }
        List<Task> tasks = taskService.getByTodoId(todoId);
        List<TaskDto> taskDtoList = tasks.stream()
                .map(TaskTransformer::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(taskDtoList);
    }

    //Логіку потрібно перемістити в Service

//    private void checkForExistenceTodoAndTask(@PathVariable("task-id") long taskId,
//                                              @PathVariable("todo-id") long todoId) {
//        try {
//            toDoService.readById(todoId);
//        } catch (IndexOutOfBoundsException exception) {
//            throw new EntityNotFoundException(String.format("ToDo with id % not found", todoId));
//            try {
//                taskService.readById(taskId);
//            } catch (IndexOutOfBoundsException e) {
//                throw new EntityNotFoundException(String.format("Task with id % not found", taskId));
//            }
//        }
//    }
}