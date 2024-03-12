package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;


import com.example.demo.models.Courses;


public interface CoursesRepository extends JpaRepository<Courses, Integer>{
    Courses findById(int id);

    // public void setCourseThumbnail(byte[] thumbnailData);
       
    
}
