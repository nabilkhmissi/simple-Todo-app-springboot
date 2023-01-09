package com.example.simplecontactapp.controller;

import com.example.simplecontactapp.Dtos.AuthRequest;
import com.example.simplecontactapp.Dtos.AuthResponse;
import com.example.simplecontactapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@RequiredArgsConstructor
public class authController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest){
        return new ResponseEntity<>(userService.login(authRequest), HttpStatus.OK);
    }
}
