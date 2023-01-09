package com.example.simplecontactapp.Dtos;

import com.example.simplecontactapp.Model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class CategoryDto {

    private Long id;

    private String title;

    private String description;

    private UserDto user;

    private List<TodoDto> todoList;

    public static Category toEntity(CategoryDto categoryDto) {
        Category category = new Category();

        category.setUser(UserDto.toEntity(categoryDto.getUser()));
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());

        return category;
    }

    public static CategoryDto fromEntity(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .description(category.getDescription())
                .todoList(
                        category.getTodoList() != null ? category.getTodoList().stream().map(TodoDto::fromEntity).collect(Collectors.toList()) : null
                )
                .build();
    }
}
