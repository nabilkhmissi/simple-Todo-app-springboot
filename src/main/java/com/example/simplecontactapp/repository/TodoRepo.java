package com.example.simplecontactapp.repository;

import com.example.simplecontactapp.Model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoRepo extends JpaRepository<Todo, Long> {

    @Query("select t from Todo t where t.category.user.id = :userId")
    List<Todo> findAllByUserId(Long userId);

    @Query("select t from Todo t where t.category.id = :catId")
    List<Todo> findAllByCategoryId(Long catId);

    @Query("select t from Todo t where t.favorite = true and t.category.user.id = :userId")
    List<Todo> findAllFavoriteByUserId(Long userId);

    @Query("select t from Todo t where t.title like :searchKey and t.category.user.id = :userId")
    List<Todo> doSearch(@Param("searchKey") String searchKey,
                        @Param("userId") Long userId);
}
