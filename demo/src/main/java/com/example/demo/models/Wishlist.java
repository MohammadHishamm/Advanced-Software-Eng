package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;

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
public class Wishlist {

   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int wishlist_id;

    @ManyToMany
    @JoinTable(
        name = "wishlist_courses",
        joinColumns = @JoinColumn(name = "wishlist_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Courses> courses = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id")
    private Student student;



   public Wishlist() {
   }

   public Wishlist(int wishlist_id, List<Courses> courses, Student student) {
      this.wishlist_id = wishlist_id;
      this.courses = courses;
      this.student = student;
   }

   public int getWishlist_id() {
      return this.wishlist_id;
   }

   public void setWishlist_id(int wishlist_id) {
      this.wishlist_id = wishlist_id;
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

   public Wishlist wishlist_id(int wishlist_id) {
      setWishlist_id(wishlist_id);
      return this;
   }

   public Wishlist courses(List<Courses> courses) {
      setCourses(courses);
      return this;
   }

   public Wishlist student(Student student) {
      setStudent(student);
      return this;
   }

   @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Wishlist)) {
            return false;
        }
        Wishlist wishlist = (Wishlist) o;
        return wishlist_id == wishlist.wishlist_id && Objects.equals(courses, wishlist.courses) && Objects.equals(student, wishlist.student);
   }

   @Override
   public int hashCode() {
      return Objects.hash(wishlist_id, courses, student);
   }

   @Override
   public String toString() {
      return "{" +
         " wishlist_id='" + getWishlist_id() + "'" +
         ", courses='" + getCourses() + "'" +
         ", student='" + getStudent() + "'" +
         "}";
   }

    
}
