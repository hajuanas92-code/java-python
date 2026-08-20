package com.example.demo.project;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // creates a book table in database
public class Book {

    @Id // defines primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increaments ids (1,2,3..)
    private Long id;
    private String title;
    private String author;
    private boolean isAvailable;

    // JPA requires empty constructor
    public Book(){}

    public Book(Long id,String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }
    // id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    // title
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    // author
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getAuthor() {
        return author;
    }
    // available
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

}
