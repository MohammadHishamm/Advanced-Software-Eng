package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@Entity
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private int course_id;

    @NotEmpty(message = "Please enter a title for the Course")
    private String course_title;

    @NotBlank(message = "Course status is required")
    private String course_status;

    @NotEmpty(message = "Please enter a description for the Course")
    private String course_description;

    @NotEmpty(message = "Please the requirements of the Course")
    private String course_requirements;

    @NotNull(message = "Course price is required")
    @Positive(message = "Course price must be a positive number")
    private Double course_price;


    
    @OneToOne
    @JoinColumn(name = "instructor_id") 
    private Instructor instructor;


    public Courses() {
    }

    public Courses(int course_id, String course_title, String course_status, String course_description, String course_requirements, Double course_price) {
        this.course_id = course_id;
        this.course_title = course_title;
        this.course_status = course_status;
        this.course_description = course_description;
        this.course_requirements = course_requirements;
        this.course_price = course_price;
    }

    public int getCourse_id() {
        return this.course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getCourse_title() {
        return this.course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_status() {
        return this.course_status;
    }

    public void setCourse_status(String course_status) {
        this.course_status = course_status;
    }

    public String getCourse_description() {
        return this.course_description;
    }

    public void setCourse_description(String course_description) {
        this.course_description = course_description;
    }

    public String getCourse_requirements() {
        return this.course_requirements;
    }

    public void setCourse_requirements(String course_requirements) {
        this.course_requirements = course_requirements;
    }

    public Double getCourse_price() {
        return this.course_price;
    }

    public void setCourse_price(Double course_price) {
        this.course_price = course_price;
    }

    public Courses course_id(int course_id) {
        setCourse_id(course_id);
        return this;
    }

    public Courses course_title(String course_title) {
        setCourse_title(course_title);
        return this;
    }

    public Courses course_status(String course_status) {
        setCourse_status(course_status);
        return this;
    }

    public Courses course_description(String course_description) {
        setCourse_description(course_description);
        return this;
    }

    public Courses course_requirements(String course_requirements) {
        setCourse_requirements(course_requirements);
        return this;
    }

    public Courses course_price(Double course_price) {
        setCourse_price(course_price);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Courses)) {
            return false;
        }
        Courses courses = (Courses) o;
        return course_id == courses.course_id && Objects.equals(course_title, courses.course_title) && Objects.equals(course_status, courses.course_status) && Objects.equals(course_description, courses.course_description) && Objects.equals(course_requirements, courses.course_requirements) && Objects.equals(course_price, courses.course_price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(course_id, course_title, course_status, course_description, course_requirements, course_price);
    }

    @Override
    public String toString() {
        return "{" +
            " course_id='" + getCourse_id() + "'" +
            ", course_title='" + getCourse_title() + "'" +
            ", course_status='" + getCourse_status() + "'" +
            ", course_description='" + getCourse_description() + "'" +
            ", course_requirements='" + getCourse_requirements() + "'" +
            ", course_price='" + getCourse_price() + "'" +
            "}";
    }


    public Courses(int course_id, String course_title, String course_status, String course_description, String course_requirements, Double course_price, Instructor instructor) {
        this.course_id = course_id;
        this.course_title = course_title;
        this.course_status = course_status;
        this.course_description = course_description;
        this.course_requirements = course_requirements;
        this.course_price = course_price;
        this.instructor = instructor;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Courses instructor(Instructor instructor) {
        setInstructor(instructor);
        return this;
    }
    

}
   