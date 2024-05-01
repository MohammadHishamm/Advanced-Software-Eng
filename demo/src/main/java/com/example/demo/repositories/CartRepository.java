package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Cart;
import com.example.demo.models.Student;


public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByStudent(Student student);
}
