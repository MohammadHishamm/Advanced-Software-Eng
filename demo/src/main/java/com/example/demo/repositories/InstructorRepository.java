package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Courses;
import com.example.demo.models.Instructor;
import com.example.demo.models.User;

public interface InstructorRepository extends JpaRepository<Instructor, Integer>
 {
 Instructor findByUser(User user);
 List<Instructor> findAll();
}
