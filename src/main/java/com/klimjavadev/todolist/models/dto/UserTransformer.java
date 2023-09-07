package com.klimjavadev.todolist.models.dto;

import com.klimjavadev.todolist.models.entity.*;

public class UserTransformer {

    public static UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail().toString(),
                user.getPassword().toString(),
                user.getRole().getId()
        );
    }

    public static User convertToEntity(UserDto userDto, Role role) {
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(role);

        return user;
    }
}
