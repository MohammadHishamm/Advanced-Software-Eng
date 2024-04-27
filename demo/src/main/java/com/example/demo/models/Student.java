package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Objects;

public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    private List<Courses> courses;

        @OneToOne
    @JoinColumn(name = "user_id") 
    private User user;

    public Student(int id, List<Courses> courses, User user) {
        this.id = id;
        this.courses = courses;
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Student user(User user) {
        setUser(user);
        return this;
    }

    public Student() {
    }

    public Student(int id, List<Courses> courses) {
        this.id = id;
        this.courses = courses;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Courses> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public Student id(int id) {
        setId(id);
        return this;
    }

    public Student courses(List<Courses> courses) {
        setCourses(courses);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return id == student.id && Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courses);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", courses='" + getCourses() + "'" +
            "}";
    }
    
}
