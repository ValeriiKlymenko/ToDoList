package com.klimjavadev.todolist.controllers;

import com.klimjavadev.todolist.models.dto.TaskResponse;
import com.klimjavadev.todolist.models.entity.Task;
import com.klimjavadev.todolist.models.entity.ToDo;
import com.klimjavadev.todolist.models.entity.User;
import com.klimjavadev.todolist.repositories.TaskRepository;
import com.klimjavadev.todolist.services.TaskService;
import com.klimjavadev.todolist.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/{user-id}/todos/{todo-id}/tasks")
public class TaskController {
//    private Logger logger = LoggerFactory.getLogger(TaskController.class);
//    UserService userService;
//    TaskService taskService;
//
//    @Autowired
//    public TaskController(UserService userService, TaskService taskService) {
//        this.userService = userService;
//        this.taskService = taskService;
//    }
//
//    @GetMapping
//    List<TaskResponse> getTasks(@PathVariable("user-id") Long userId, @PathVariable("todo-id") Long todoId){
////тут можна обійтися тільки taskService
////        Optional<User> user = Optional.ofNullable(userService.readById(userId));
//
//        if (!taskService.readById(todoId).equals(taskService.getByTodoId(todoId).contains(userId))){ //чи належить todo user
//            //bed request
//        }
//        List<Task> taskList = taskService.getByTodoId(todoId);
//
//        return taskList.stream()
//                .map(TaskResponse::new)
//                .collect(Collectors.toList());
//    }
//
////    @PostMapping
//    //тут потрібно використовувати todoService
}
