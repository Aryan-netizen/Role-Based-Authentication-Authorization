package com.tatkal.backend_app.services.impl;

import com.tatkal.backend_app.dtos.UserDto;
import com.tatkal.backend_app.services.AuthService;
import com.tatkal.backend_app.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    @Override
    public UserDto registerUser(UserDto userDto) {
        UserDto userDto1 = userService.createUser(userDto);
        return userDto1;
    }
}
