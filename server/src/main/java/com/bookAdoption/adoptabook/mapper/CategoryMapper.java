package com.bookAdoption.adoptabook.mapper;

import com.bookAdoption.adoptabook.dto.CategoryDTO;
import com.bookAdoption.adoptabook.entity.Book;
import com.bookAdoption.adoptabook.entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryDTO toCategoryDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(
            category.getId(),
            category.getName(),
            category.getBooks().stream().map(Book::getId).collect(Collectors.toList())
        );
    }

    public static Category toCategoryEntity(CategoryDTO dto, List<Book> books) {
        if (dto == null) {
            return null;
        }
        return new Category(
            dto.id(),
            dto.name(),
            books
        );
    }
}
