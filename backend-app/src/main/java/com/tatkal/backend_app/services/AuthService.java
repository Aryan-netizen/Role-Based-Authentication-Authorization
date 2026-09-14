package com.tatkal.backend_app.services;


import com.tatkal.backend_app.dtos.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDto registerUser(UserDto userDto);
}
