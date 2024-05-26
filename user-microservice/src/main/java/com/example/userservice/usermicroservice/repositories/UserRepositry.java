package com.example.userservice.usermicroservice.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.usermicroservice.models.User;

// package com.example.userservice.usermicroservice;

public interface UserRepositry extends JpaRepository<User, Integer>{

    User findByEmail(String email);

    List<User> findAll();
    
}
