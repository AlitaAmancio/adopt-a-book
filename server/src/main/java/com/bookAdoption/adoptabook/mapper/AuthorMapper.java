package com.bookAdoption.adoptabook.mapper;

import com.bookAdoption.adoptabook.dto.AuthorDTO;
import com.bookAdoption.adoptabook.entity.Author;
import com.bookAdoption.adoptabook.entity.Book;

import java.util.List;
import java.util.stream.Collectors;

public class AuthorMapper {

    public static AuthorDTO toAuthorDTO(Author author) {
        if (author == null) {
            return null;
        }
        return new AuthorDTO(
            author.getId(),
            author.getName(),
            author.getBiography(),
            author.getBirthdate(),
            author.getBooks().stream().map(Book::getId).collect(Collectors.toList())
        );
    }

    public static Author toAuthorEntity(AuthorDTO dto, List<Book> books) {
        if (dto == null) {
            return null;
        }
        return new Author(
            dto.id(),
            dto.name(),
            dto.biography(),
            dto.birthdate(),
            books
        );
    }
}
