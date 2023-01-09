package com.example.simplecontactapp.controller;

import com.example.simplecontactapp.Dtos.AuthResponse;
import com.example.simplecontactapp.Dtos.UserDto;
import com.example.simplecontactapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<AuthResponse> createUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }
    @GetMapping("/find/{userId}")
    public ResponseEntity<UserDto> findUser(@PathVariable Long userId){
        return new ResponseEntity<>(userService.findById(userId), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long userId){
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{userId}")
    public ResponseEntity<AuthResponse> updateUser(@PathVariable Long userId,
                                              @RequestBody UserDto userDto){
        return new ResponseEntity<>( userService.update(userDto, userId), HttpStatus.OK);
    }
}
