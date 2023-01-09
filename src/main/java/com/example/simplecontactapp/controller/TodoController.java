package com.example.simplecontactapp.controller;

import com.example.simplecontactapp.Dtos.TodoDto;
import com.example.simplecontactapp.services.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<TodoDto> createTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.create(todoDto), HttpStatus.CREATED);
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return new ResponseEntity(todoService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/find/user/{userId}")
    public ResponseEntity<List<TodoDto>> getAllTodosByUserId(@PathVariable Long userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity(todoService.findAllTodosByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/find/category/{catId}")
    public ResponseEntity<List<TodoDto>> getAllTodosCategoryId(@PathVariable Long catId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity(todoService.findByCategoryId(catId), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/favorite/find")
    public ResponseEntity<List<TodoDto>> getAllFavoriteTodoByUserId(@PathVariable Long userId) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity(todoService.findAllFavoriteByUserId(userId), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}/doSearh/{searchKey}")
    public ResponseEntity<List<TodoDto>> getAllBySearchKeyAndUserId(@PathVariable Long userId,
                                                                    @PathVariable String searchKey) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        return new ResponseEntity(todoService.doSearch(searchKey, userId), HttpStatus.OK);
    }

    @GetMapping("/find/{todoId}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long todoId) {
        return  new ResponseEntity<>(todoService.findById(todoId), HttpStatus.OK);
    }
    @GetMapping("/favorite/{todoId}")
    public ResponseEntity changeFavorite(@PathVariable Long todoId) {
        todoService.doLikeTodo(todoId);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/done/{todoId}")
    public ResponseEntity checkAsDone(@PathVariable Long todoId) {
        todoService.checkAsDone(todoId);
        return  new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTodo(@PathVariable Long id) {
        todoService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
