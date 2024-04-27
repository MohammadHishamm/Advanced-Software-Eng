package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Instructor;

public interface StudentRepository extends JpaRepository<Instructor, Integer>
{
    
}
