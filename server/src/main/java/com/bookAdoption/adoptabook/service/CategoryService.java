package com.bookAdoption.adoptabook.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookAdoption.adoptabook.dto.CategoryDTO;
import com.bookAdoption.adoptabook.entity.Book;
import com.bookAdoption.adoptabook.entity.Category;
import com.bookAdoption.adoptabook.mapper.BookMapper;
import com.bookAdoption.adoptabook.mapper.CategoryMapper;
import com.bookAdoption.adoptabook.repository.BookInterface;
import com.bookAdoption.adoptabook.repository.CategoryInterface;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryInterface categoryInterface;

    @Autowired
    private BookInterface bookInterface;

    @Autowired
    @Lazy
    private BookMapper bookMapper;

    public List<CategoryDTO> getAllCategories() {
        try {
            return categoryInterface.findAll().stream()
                    .map(CategoryMapper::toCategoryDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as categorias", e);
        }
    }

    public Optional<CategoryDTO> getCategoryById(Long id) {
        try {
            Optional<Category> categoryOptional = categoryInterface.findById(id);
            return categoryOptional.map(CategoryMapper::toCategoryDTO);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar categoria por ID: " + id, e);
        }
    }

    public List<CategoryDTO> getCategoriesByName(String name) {
        try {
            Optional<List<Category>> categoriesOptional = categoryInterface.findByNameContainingIgnoreCase(name);
            List<Category> categories = categoriesOptional.orElse(Collections.emptyList());
            return categories.stream()
                    .map(CategoryMapper::toCategoryDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar categorias por nome: " + name, e);
        }
    }

    public CategoryDTO saveCategory(CategoryDTO categoryDto) {
        List<Book> books = bookInterface.findAllById(categoryDto.bookIds());
        Category category = CategoryMapper.toCategoryEntity(categoryDto, books);
        try {
            Category savedCategory = categoryInterface.save(category);
            return CategoryMapper.toCategoryDTO(savedCategory);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar categoria: " + categoryDto.name(), e);
        }
    }

    public CategoryDTO updateCategoryById(Long id, CategoryDTO newCategoryDto) {
        try {
            Optional<Category> categoryOptional = categoryInterface.findById(id);
            if (categoryOptional.isPresent()) {
                Category existingCategory = categoryOptional.get();
                existingCategory.setName(newCategoryDto.name());
                
                Category updatedCategory = categoryInterface.save(existingCategory);
                return CategoryMapper.toCategoryDTO(updatedCategory);
            } else {
                throw new RuntimeException("Categoria não encontrada com o ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar categoria por ID: " + id, e);
        }
    }

    public void deleteCategoryById(Long id) {
        try {
            Category category = categoryInterface.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com ID: " + id));

            for (Book book : category.getBooks()) {
                book.getCategories().remove(category);
                bookInterface.save(book); 
            }
            categoryInterface.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar categoria por ID: " + id, e);
        }
    }
}
