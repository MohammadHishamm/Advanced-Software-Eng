package com.example.userservice.usermicroservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.usermicroservice.models.Instructor;
import com.example.userservice.usermicroservice.repositories.InstructorRepository;

@RestController
@RequestMapping("/teacher")
public class InstructorController {
    
    @Autowired
    private InstructorRepository instructorRepository;
   

    @PostMapping("/teacherform")
    public ResponseEntity<Instructor> registerInstructor(@RequestBody Instructor instructor){
        instructor.setStatus("Observing");
        this.instructorRepository.save(instructor); 
        return new ResponseEntity<>(instructor, HttpStatus.CREATED);
    }

    
}
