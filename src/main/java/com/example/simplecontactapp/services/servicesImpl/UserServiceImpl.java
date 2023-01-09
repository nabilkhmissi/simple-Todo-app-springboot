package com.example.simplecontactapp.services.servicesImpl;

import com.example.simplecontactapp.Dtos.AuthRequest;
import com.example.simplecontactapp.Dtos.AuthResponse;
import com.example.simplecontactapp.Dtos.UserDto;
import com.example.simplecontactapp.Model.User;
import com.example.simplecontactapp.exceptions.EntityNotFoundException;
import com.example.simplecontactapp.exceptions.ErrorCodes;
import com.example.simplecontactapp.exceptions.InvalidEntityException;
import com.example.simplecontactapp.repository.UserRepo;
import com.example.simplecontactapp.services.UserService;
import com.example.simplecontactapp.validators.UserValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public AuthResponse createUser(UserDto user) {
        List<String> errors = UserValidators.validateUser(user);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Invalid user details", ErrorCodes.USER_NOT_VALID, errors);
        }
        User u = userRepo.save(UserDto.toEntity(user));
        return AuthResponse.builder()
                .firstName(u.getFirstName())
                .lastName(u.getLastName())
                .id(u.getId())
                .userName(u.getUserName())
                .email(u.getEmail())
                .build();
    }

    @Override
    public void delete(Long userId) {
        if(userId == null){
            throw new EntityNotFoundException("user with this ID : " + userId + " not found !");
        }
        userRepo.deleteById(userId);
    }

    @Override
    public UserDto findById(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new EntityNotFoundException("user with this ID :" + userId + " not found", ErrorCodes.USER_NOT_FOUND));
        return UserDto.fromEntity(user);
    }

    @Override
    public AuthResponse update(UserDto user, Long userId) {
        List<String> errors = UserValidators.validateUser(user);
        if(userId == null){
            throw new EntityNotFoundException("user with this ID : " + userId + " not found !");
        }
        if(!errors.isEmpty()){
            throw new InvalidEntityException("please provide all details", ErrorCodes.USER_NOT_VALID, errors);
        }

        user.setId(userId);
        User updateUser = userRepo.save(UserDto.toEntity(user));
        return AuthResponse.builder()
                .id(updateUser.getId())
                .firstName(updateUser.getFirstName())
                .lastName(updateUser.getLastName())
                .userName(updateUser.getUserName())
                .email(updateUser.getEmail())
                .build();
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        User user = userRepo.findByEmailAndPassword(authRequest.getEmail(), authRequest.getPassword());
        if(user == null){
            throw new EntityNotFoundException("user not found with email : "
                    + authRequest.getEmail() + " and password <HIDDEN PASSWORD>", ErrorCodes.USER_NOT_FOUND);
        }
        return AuthResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}
