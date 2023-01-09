package com.example.simplecontactapp.Dtos;

import com.example.simplecontactapp.Model.Todo;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Builder
@Data
public class TodoDto {

    private Long id;

    private String title;

    private String description;

    private ZonedDateTime creationDate;

    private boolean done;

    private boolean favorite;

    private CategoryDto category;

    public static Todo toEntity(TodoDto todoDto) {
        final Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setDone(todoDto.isDone());
        todo.setFavorite(todoDto.isFavorite());
        todo.setCreationDate(ZonedDateTime.now());
        todo.setCategory(CategoryDto.toEntity(todoDto.getCategory()));

        return todo;
    }

    public static TodoDto fromEntity(Todo todo) {
        return TodoDto.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .creationDate(todo.getCreationDate())
                .done(todo.isDone())
                .favorite(todo.isFavorite())
                .build();
    }
}
