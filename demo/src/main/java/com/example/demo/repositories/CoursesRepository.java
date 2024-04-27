package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.models.Courses;



public interface CoursesRepository extends JpaRepository<Courses, Integer>{
    Courses findById(int id);
    List<Courses> findAll();
    // public void setCourseThumbnail(byte[] thumbnailData);
       
    
}
