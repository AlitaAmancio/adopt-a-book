package com.bookAdoption.adoptabook.mapper;

import com.bookAdoption.adoptabook.dto.AuthorDTO;
import com.bookAdoption.adoptabook.entity.Author;

public class AuthorMapper {
    
    public static AuthorDTO toDTO(Author author) {
        AuthorDTO dto = new AuthorDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setBiography(author.getBiography());
        dto.setBirthdate(author.getBirthdate());
        dto.setBooks(author.getBooks());
        return dto;
    }

    public static Author toEntity(AuthorDTO dto) {
        Author author = new Author();
        author.setId(dto.getId());
        author.setName(dto.getName());
        author.setBiography(dto.getBiography());
        author.setBirthdate(dto.getBirthdate());
        author.setBooks(dto.getBooks());
        return author;
    }
    
}
