package com.example.simplecontactapp.repository;

import com.example.simplecontactapp.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email = :email and u.password = :password")
    User findByEmailAndPassword(String email, String password);
}
