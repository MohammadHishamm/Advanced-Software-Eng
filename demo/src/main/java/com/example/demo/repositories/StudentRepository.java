package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Instructor;
import com.example.demo.models.Student;
import com.example.demo.models.User;

public interface StudentRepository extends JpaRepository<Instructor, Integer>
{
     Student findByUser(User user);
}
