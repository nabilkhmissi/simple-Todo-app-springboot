package com.example.simplecontactapp.services.servicesImpl;

import com.example.simplecontactapp.Dtos.AuthRequest;
import com.example.simplecontactapp.Dtos.AuthResponse;
import com.example.simplecontactapp.Dtos.CategoryDto;
import com.example.simplecontactapp.Dtos.UserDto;
import com.example.simplecontactapp.Model.Category;
import com.example.simplecontactapp.Model.User;
import com.example.simplecontactapp.exceptions.EntityNotFoundException;
import com.example.simplecontactapp.exceptions.ErrorCodes;
import com.example.simplecontactapp.exceptions.InvalidEntityException;
import com.example.simplecontactapp.repository.CategoryRepo;
import com.example.simplecontactapp.repository.UserRepo;
import com.example.simplecontactapp.services.CategoryService;
import com.example.simplecontactapp.services.UserService;
import com.example.simplecontactapp.validators.CategoryValidators;
import com.example.simplecontactapp.validators.UserValidators;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public CategoryDto create(CategoryDto category) {
        List<String> errors = CategoryValidators.validateCategory(category);
        if(!errors.isEmpty()){
            throw new InvalidEntityException("Invalid category details", ErrorCodes.CATEGORY_NOT_VALID, errors);
        }
        return CategoryDto.fromEntity(categoryRepo.save(CategoryDto.toEntity(category)));
    }

    @Override
    public void delete(Long id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public List<CategoryDto> findCategoryByUserId(Long id) {
        return categoryRepo.findByUserId(id).stream().map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Category c = categoryRepo.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("category not found with ID : " + id)
        );
        return CategoryDto.fromEntity(c);
    }

    @Override
    public List<CategoryDto> getAllTodoByCategoriesForToday(Long userId) {
        return categoryRepo.getAllTodoByCategoriesForToday(ZonedDateTime.now().withHour(0).withMinute(0),
                        ZonedDateTime.now().withHour(23).withMinute(59), userId)
                .stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }
}
