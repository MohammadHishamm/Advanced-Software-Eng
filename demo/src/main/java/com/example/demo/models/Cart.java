package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cart_id;

    @ManyToMany
    @JoinTable(
        name = "cart_courses",
        joinColumns = @JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Courses> courses = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id")
    private Student student;


    public Cart() {
    }

    public Cart(int cart_id, List<Courses> courses, Student student) {
        this.cart_id = cart_id;
        this.courses = courses;
        this.student = student;
    }

    public int getCart_id() {
        return this.cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public List<Courses> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Cart cart_id(int cart_id) {
        setCart_id(cart_id);
        return this;
    }

    public Cart courses(List<Courses> courses) {
        setCourses(courses);
        return this;
    }

    public Cart student(Student student) {
        setStudent(student);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart cart = (Cart) o;
        return cart_id == cart.cart_id && Objects.equals(courses, cart.courses) && Objects.equals(student, cart.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart_id, courses, student);
    }

    @Override
    public String toString() {
        return "{" +
            " cart_id='" + getCart_id() + "'" +
            ", courses='" + getCourses() + "'" +
            ", student='" + getStudent() + "'" +
            "}";
    }

    

}
