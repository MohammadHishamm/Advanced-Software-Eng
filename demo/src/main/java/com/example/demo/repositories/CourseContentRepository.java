package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.CourseContent;
import com.example.demo.models.Courses;


public interface CourseContentRepository extends JpaRepository<CourseContent, Integer> {
   CourseContent findById(int id);
}
