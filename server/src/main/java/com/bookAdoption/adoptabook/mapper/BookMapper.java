package com.bookAdoption.adoptabook.mapper;

import com.bookAdoption.adoptabook.dto.BookDTO;
import com.bookAdoption.adoptabook.entity.Author;
import com.bookAdoption.adoptabook.entity.Book;
import com.bookAdoption.adoptabook.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class BookMapper {

    public static BookDTO toBookDTO(Book book) {
        if (book == null) {
            return null;
        }
        return new BookDTO(
            book.getId(),
            book.getTitle(),
            book.getDescription(),
            book.getCoverType(),
            book.getPublicationDate(),
            book.getAuthors().stream().map(Author::getId).collect(Collectors.toList()),
            book.getCategories().stream().map(Category::getId).collect(Collectors.toList())
        );
    }

    public static Book toBookEntity(BookDTO dto, List<Author> authors, List<Category> categories) {
        if (dto == null) {
            return null;
        }
        return new Book(
            dto.id(),
            dto.title(),
            dto.description(),
            dto.coverType(),
            dto.publicationDate(),
            authors,
            categories
        );
    }
}
