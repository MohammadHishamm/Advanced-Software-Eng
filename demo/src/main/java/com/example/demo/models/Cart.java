package com.example.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cart_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<Courses> courses;


    public Cart() {
    }

    public Cart(int cart_id, User user, List<Courses> courses) {
        this.cart_id = cart_id;
        this.user = user;
        this.courses = courses;
    }

    public int getCart_id() {
        return this.cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Courses> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }

    public Cart cart_id(int cart_id) {
        setCart_id(cart_id);
        return this;
    }

    public Cart user(User user) {
        setUser(user);
        return this;
    }

    public Cart courses(List<Courses> courses) {
        setCourses(courses);
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
        return cart_id == cart.cart_id && Objects.equals(user, cart.user) && Objects.equals(courses, cart.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart_id, user, courses);
    }

    @Override
    public String toString() {
        return "{" +
            " cart_id='" + getCart_id() + "'" +
            ", user='" + getUser() + "'" +
            ", courses='" + getCourses() + "'" +
            "}";
    }
    

}
