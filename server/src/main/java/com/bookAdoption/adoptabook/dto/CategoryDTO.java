package com.bookAdoption.adoptabook.dto;

import java.util.List;

import com.bookAdoption.adoptabook.entity.Book;

public class CategoryDTO {
    private Long id;
    private String name;
    private List<Book> books;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

}
