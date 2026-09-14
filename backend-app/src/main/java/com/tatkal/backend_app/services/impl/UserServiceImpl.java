package com.tatkal.backend_app.services.impl;

import com.tatkal.backend_app.dtos.UserDto;
import com.tatkal.backend_app.entities.Provider;
import com.tatkal.backend_app.entities.User;
import com.tatkal.backend_app.exceptions.ResourseNotFoundException;
import com.tatkal.backend_app.helpers.UserHelper;
import com.tatkal.backend_app.repositories.UserRepository;
import com.tatkal.backend_app.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;



    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        if(userDto.getEmail() == null || userDto.getEmail().isBlank()){
                throw new IllegalArgumentException("Email is Required");
        }

        if (userRepository.existsByEmail(userDto.getEmail())){
            throw new IllegalArgumentException("Email is already exists");
        }

        User user = modelMapper.map(userDto, User.class);

        user.setProvider(userDto.getProvider()!=null? user.getProvider(): Provider.LOCAL);

        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(
                ()-> new ResourseNotFoundException("User not Found with given email id")
        );
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        UUID id = UserHelper.parseUUID(userId);
        User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("user not found by given Id"));
        if(userDto.getName() != null || !userDto.getName().isBlank()) existingUser.setName(userDto.getName());
        if(userDto.getImage() != null) existingUser.setImage(userDto.getImage());
        if(userDto.getProvider() != null) existingUser.setProvider(userDto.getProvider());
        if(userDto.getPassword() != null) existingUser.setPassword(userDto.getPassword());
        existingUser.setEnable(userDto.isEnable());
        existingUser.setUpdatedAt(Instant.now());
        User saveduser = userRepository.save(existingUser);

        return modelMapper.map(saveduser,UserDto.class);
    }

    @Override
    public void deleteUser(String userId) {
        UUID id = UserHelper.parseUUID(userId);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("user not found by given Id"));
        userRepository.delete(user);

    }

    @Override
    public UserDto getUserById(String userId) {
        UUID id = UserHelper.parseUUID(userId);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourseNotFoundException("user not found by given Id"));

        return modelMapper.map(user,UserDto.class);
    }

    @Override
    @Transactional
    public Iterable<UserDto> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map( user -> modelMapper.map(user,UserDto.class))
                .toList();
    }
}
