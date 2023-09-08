package com.klimjavadev.todolist.models.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.klimjavadev.todolist.models.entity.User;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserRequest {
    String firstName;
    String lastName;
    String email;
    String role;

    public UserRequest(User user) {
        firstName = user.getFirstName();
        lastName = user.getLastName();
        email = user.getEmail();
        role = user.getRole().getName();
    }
}
