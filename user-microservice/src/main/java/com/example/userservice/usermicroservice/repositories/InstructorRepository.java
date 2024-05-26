package com.example.userservice.usermicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.userservice.usermicroservice.models.Instructor;
import com.example.userservice.usermicroservice.models.User;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Instructor findByUser(User user);
    
}
