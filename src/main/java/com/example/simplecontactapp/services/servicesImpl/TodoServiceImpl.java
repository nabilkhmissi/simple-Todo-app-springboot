package com.example.simplecontactapp.services.servicesImpl;

import com.example.simplecontactapp.Dtos.TodoDto;
import com.example.simplecontactapp.Model.Todo;
import com.example.simplecontactapp.exceptions.EntityNotFoundException;
import com.example.simplecontactapp.exceptions.ErrorCodes;
import com.example.simplecontactapp.exceptions.InvalidEntityException;
import com.example.simplecontactapp.repository.TodoRepo;
import com.example.simplecontactapp.services.TodoService;
import com.example.simplecontactapp.validators.TodoValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepo todoRepo;


    @Override
    public TodoDto create(TodoDto todo) {
        List<String> errors = TodoValidators.validateTodo(todo);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("todo not valid", ErrorCodes.TODO_NOT_VALID, errors);
        }
        return TodoDto.fromEntity(todoRepo.save(TodoDto.toEntity(todo)));
    }

    @Override
    public void delete(Long id) {
        todoRepo.deleteById(id);
    }

    @Override
    public List<TodoDto> findAllTodosByUserId(Long userId) {
        return todoRepo.findAllByUserId(userId).stream().map(TodoDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findAll() {
        return todoRepo.findAll().stream().map(TodoDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public TodoDto findById(Long todoId) {
        Todo todo = todoRepo.findById(todoId).orElseThrow(
                ()-> new EntityNotFoundException("todo not found with ID: " + todoId));
        return TodoDto.fromEntity(todo);
    }

    @Override
    public List<TodoDto> findByCategoryId(Long catId) {
        return todoRepo.findAllByCategoryId(catId)
                .stream().map(TodoDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> findAllFavoriteByUserId(Long userId) {
        return todoRepo.findAllFavoriteByUserId(userId)
                .stream().map(TodoDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<TodoDto> doSearch(String searchKey, Long userId) {
        return todoRepo.doSearch(searchKey, userId)
                .stream().map(TodoDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public void doLikeTodo(Long id) {
        Todo todo = todoRepo.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("TODO not found with ID " + id, ErrorCodes.TODO_NOT_FOUND)
        );
        todo.setFavorite(!todo.isFavorite());
        todoRepo.save(todo);
    }

    @Override
    public void checkAsDone(Long id) {
        Todo todo = todoRepo.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("TODO not found with ID " + id, ErrorCodes.TODO_NOT_FOUND)
        );
        todo.setDone(!todo.isDone());
        todoRepo.save(todo);
    }
}
