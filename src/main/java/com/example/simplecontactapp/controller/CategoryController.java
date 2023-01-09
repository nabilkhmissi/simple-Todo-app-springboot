package com.example.simplecontactapp.controller;

import com.example.simplecontactapp.Dtos.CategoryDto;
import com.example.simplecontactapp.Dtos.TodoDto;
import com.example.simplecontactapp.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto category){
        return new ResponseEntity<>(categoryService.create(category), HttpStatus.CREATED);
    }
    @GetMapping("/find/user/{id}")
    public ResponseEntity<List<CategoryDto>> findCategoryByUserId(@PathVariable Long id){
        return new ResponseEntity<>(categoryService.findCategoryByUserId(id), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        categoryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/todos/today/{userId}")
    public ResponseEntity<List<TodoDto>> getAllTodoByCategoriesForToday(Long userId) {
        return new ResponseEntity(categoryService.getAllTodoByCategoriesForToday(userId), HttpStatus.OK);
    }
}
