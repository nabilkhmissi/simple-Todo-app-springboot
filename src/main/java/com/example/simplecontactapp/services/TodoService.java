package com.example.simplecontactapp.services;

import com.example.simplecontactapp.Dtos.TodoDto;
import com.example.simplecontactapp.Model.Todo;

import java.util.List;

public interface TodoService {
    TodoDto create(TodoDto todo);
    void delete(Long id);
    List<TodoDto> findAllTodosByUserId(Long userId);
    List<TodoDto> findAll();
    TodoDto findById(Long todoId);
    List<TodoDto> findByCategoryId(Long todoId);
    List<TodoDto> findAllFavoriteByUserId(Long todoId);
    List<TodoDto> doSearch(String searchKey, Long userId);
    void doLikeTodo(Long id);
    void checkAsDone(Long id);
}
