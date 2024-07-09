package com.bookAdoption.adoptabook.mapper;

import com.bookAdoption.adoptabook.dto.BookDTO;
import com.bookAdoption.adoptabook.entity.Book;

public class BookMapper {
    
    public static BookDTO toDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setDescription(book.getDescription());
        dto.setCoverType(book.getCoverType());
        dto.setPublicationDate(book.getPublicationDate());
        dto.setAuthor(book.getAuthor());
        dto.setCategories(book.getCategories());
        return dto;
    }

    public static Book toEntity(BookDTO dto) {
        Book book = new Book();
        book.setId(book.getId());
        book.setTitle(book.getTitle());
        book.setDescription(book.getDescription());
        book.setCoverType(book.getCoverType());
        book.setPublicationDate(book.getPublicationDate());
        book.setAuthor(book.getAuthor());
        book.setCategories(book.getCategories());
        return book;
    }

}

