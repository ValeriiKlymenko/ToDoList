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

}
