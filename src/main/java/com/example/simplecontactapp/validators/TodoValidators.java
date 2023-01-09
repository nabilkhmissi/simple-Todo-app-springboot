package com.example.simplecontactapp.validators;

import com.example.simplecontactapp.Dtos.TodoDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TodoValidators {

    public static List<String> validateTodo(TodoDto todo){
        List<String> errors = new ArrayList<>();
        if (todo == null) {
            errors.add("Please add a the title");
            errors.add("Please add a description");
            errors.add("Please add a category");
            return errors;
        }
       if(StringUtils.isEmpty(todo.getTitle())){
           errors.add("Please add a title");
       }
       if(StringUtils.isEmpty(todo.getDescription())){
           errors.add("Please add a description");
       }
       if(todo.getCategory() == null){
           errors.add("Please add a category");
       }
        return errors;
    }

}
