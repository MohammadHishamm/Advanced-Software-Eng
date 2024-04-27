package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Instructor;
import com.example.demo.models.User;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>
 {
 Instructor findByUser(User user);
}
