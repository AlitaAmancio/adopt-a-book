package com.bookAdoption.adoptabook.mapper;

import com.bookAdoption.adoptabook.dto.CategoryDTO;
import com.bookAdoption.adoptabook.entity.Category;

public class CategoryMapper {
    
    public static CategoryDTO toDto(Category category) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setBooks(category.getBooks());
        return dto;
    }

    public static Category toEntity(CategoryDTO dto) {
        Category category = new Category();
        category.setId(category.getId());
        category.setName(category.getName());
        category.setBooks(category.getBooks());
        return category;
    }

}