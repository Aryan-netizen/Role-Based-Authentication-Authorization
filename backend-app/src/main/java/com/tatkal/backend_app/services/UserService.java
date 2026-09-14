package com.tatkal.backend_app.services;

import com.tatkal.backend_app.dtos.UserDto;
import com.tatkal.backend_app.entities.User;

import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByEmail(String email);
    UserDto updateUser(UserDto userDto, String userId);
    void deleteUser(String userId);
    UserDto getUserById(String userId);
    Iterable<UserDto> getAllUsers();
}
