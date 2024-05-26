package com.example.demo.Services;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Instructor;

@Service
public class InstructorService {

    private RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8083"; // Microservice base URL
    public InstructorService() {
        this.restTemplate = new RestTemplate();
    }

    public void save(Instructor instructor) {
        String url = baseUrl + "/teacher/teacherform"; // Corrected URL
        this.restTemplate.postForObject(url, instructor, Instructor.class);
    }
}

