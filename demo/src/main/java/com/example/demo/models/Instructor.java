package com.example.demo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class Instructor 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Gender;
    private String Position;
    private String Language;
    private String Country;
    private String Pdf;
    private String Comment;


    public Instructor() {
    }

    public Instructor(int id, String Gender, String Position, String Language, String Country, String Pdf, String Comment) {
        this.id = id;
        this.Gender = Gender;
        this.Position = Position;
        this.Language = Language;
        this.Country = Country;
        this.Pdf = Pdf;
        this.Comment = Comment;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return this.Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPosition() {
        return this.Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getLanguage() {
        return this.Language;
    }

    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String getCountry() {
        return this.Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getPdf() {
        return this.Pdf;
    }

    public void setPdf(String Pdf) {
        this.Pdf = Pdf;
    }

    public String getComment() {
        return this.Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public Instructor id(int id) {
        setId(id);
        return this;
    }

    public Instructor Gender(String Gender) {
        setGender(Gender);
        return this;
    }

    public Instructor Position(String Position) {
        setPosition(Position);
        return this;
    }

    public Instructor Language(String Language) {
        setLanguage(Language);
        return this;
    }

    public Instructor Country(String Country) {
        setCountry(Country);
        return this;
    }

    public Instructor Pdf(String Pdf) {
        setPdf(Pdf);
        return this;
    }

    public Instructor Comment(String Comment) {
        setComment(Comment);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Instructor)) {
            return false;
        }
        Instructor instructor = (Instructor) o;
        return id == instructor.id && Objects.equals(Gender, instructor.Gender) && Objects.equals(Position, instructor.Position) && Objects.equals(Language, instructor.Language) && Objects.equals(Country, instructor.Country) && Objects.equals(Pdf, instructor.Pdf) && Objects.equals(Comment, instructor.Comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Gender, Position, Language, Country, Pdf, Comment);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", Gender='" + getGender() + "'" +
            ", Position='" + getPosition() + "'" +
            ", Language='" + getLanguage() + "'" +
            ", Country='" + getCountry() + "'" +
            ", Pdf='" + getPdf() + "'" +
            ", Comment='" + getComment() + "'" +
            "}";
    }
    
}
