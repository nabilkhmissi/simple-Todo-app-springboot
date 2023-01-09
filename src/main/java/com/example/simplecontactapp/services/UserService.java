package com.example.simplecontactapp.services;

import com.example.simplecontactapp.Dtos.AuthRequest;
import com.example.simplecontactapp.Dtos.AuthResponse;
import com.example.simplecontactapp.Dtos.UserDto;

public interface UserService {

    AuthResponse createUser(UserDto user);
    void delete(Long userId);
    UserDto findById(Long userId);
    AuthResponse update(UserDto user, Long id);
    AuthResponse login(AuthRequest authRequest);
}
