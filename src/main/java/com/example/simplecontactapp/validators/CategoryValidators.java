package com.example.simplecontactapp.validators;

import com.example.simplecontactapp.Dtos.CategoryDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryValidators {

    public static List<String> validateCategory(CategoryDto category){
        List<String> errors = new ArrayList<>();
        if (category == null) {
            errors.add("Please add a the title");
            errors.add("Please add a description");
            return errors;
        }
       if(StringUtils.isEmpty(category.getTitle())){
           errors.add("Please add a title");
       }
       if(StringUtils.isEmpty(category.getDescription())){
           errors.add("Please add a description");
       }
        return errors;
    }

}
