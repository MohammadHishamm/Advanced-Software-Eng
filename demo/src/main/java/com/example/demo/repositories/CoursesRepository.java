package com.example.demo.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Courses;
import com.example.demo.models.User;

public interface CoursesRepository extends JpaRepository<Courses, Integer>{
    Courses findById(int id);

    // public void setCourseThumbnail(byte[] thumbnailData);
       
    
}
