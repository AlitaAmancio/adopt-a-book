package com.bookAdoption.adoptabook.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String coverType;
    private Date publicationDate;
    @ManyToMany
    private List<Author> author;
    @ManyToMany
    private List<Category> categories;

    // Constructor vazio
    public Book() {

    }

    // Constructor com todos os campos
    public Book(Long id, String title, String description, String coverType, Date publicationDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.coverType = coverType;
        this.publicationDate = publicationDate;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
    this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
}
