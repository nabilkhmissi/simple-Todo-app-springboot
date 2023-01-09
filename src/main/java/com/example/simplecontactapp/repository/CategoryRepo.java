package com.example.simplecontactapp.repository;

import com.example.simplecontactapp.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query("select c from Category c where c.user.id = :userId")
    List<Category> findByUserId(@Param("userId") Long userId);

    @Query("select c from Category c inner join Todo t on t.category.id = c.id where t.creationDate >= :startDate and t.creationDate <= :endDate and c.user.id = :userId")
    List<Category> getAllTodoByCategoriesForToday(@Param("startDate") ZonedDateTime startDate,
                                                  @Param("endDate") ZonedDateTime endDate,
                                                  @Param("userId")Long userId);
}
