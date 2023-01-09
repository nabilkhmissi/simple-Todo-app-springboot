package com.example.simplecontactapp.validators;

import com.example.simplecontactapp.Dtos.UserDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UserValidators {

    public static List<String> validateUser(UserDto user){
        List<String> errors = new ArrayList<>();
        if (user == null) {
            errors.add("Please fill the First name");
            errors.add("Please fill the Last name");
            errors.add("Please fill the User name");
            errors.add("Please fill the user Email");
            errors.add("Please fill the User Password");
            return errors;
        }
       if(StringUtils.isEmpty(user.getFirstName())){
           errors.add("Please fill the First name");
       }
       if(StringUtils.isEmpty(user.getLastName())){
           errors.add("Please fill the Last name");
       }
       if(StringUtils.isEmpty(user.getUserName())){
           errors.add("Please fill the Username");
       }
       if(StringUtils.isEmpty(user.getEmail())){
           errors.add("Please fill the Email");
       }
       if(user.getPassword() == null || user.getPassword() == ""){
           errors.add("Please fill the Password");
       }
        return errors;
    }

}
