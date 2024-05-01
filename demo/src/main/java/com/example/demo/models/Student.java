package com.example.demo.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class Student 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "student_courses",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Courses> courses;

    @OneToOne
    @JoinColumn(name = "user_id") 
    private User user;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Wishlist wishlist;



    public Student() {
    }

    public Student(int id, List<Courses> courses, User user, Wishlist wishlist) {
        this.id = id;
        this.courses = courses;
        this.user = user;
        this.wishlist = wishlist;
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

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wishlist getWishlist() {
        return this.wishlist;
    }

    public void setWishlist(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    public Student id(int id) {
        setId(id);
        return this;
    }

    public Student courses(List<Courses> courses) {
        setCourses(courses);
        return this;
    }

    public Student user(User user) {
        setUser(user);
        return this;
    }

    public Student wishlist(Wishlist wishlist) {
        setWishlist(wishlist);
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
        return id == student.id && Objects.equals(courses, student.courses) && Objects.equals(user, student.user) && Objects.equals(wishlist, student.wishlist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courses, user, wishlist);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", courses='" + getCourses() + "'" +
            ", user='" + getUser() + "'" +
            ", wishlist='" + getWishlist() + "'" +
            "}";
    }


   
}