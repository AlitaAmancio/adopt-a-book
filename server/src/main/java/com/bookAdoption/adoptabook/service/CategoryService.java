package com.bookAdoption.adoptabook.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookAdoption.adoptabook.entity.Category;
import com.bookAdoption.adoptabook.repository.CategoryInterface;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryInterface categoryInterface;

    public List<Category> getAllCategories() {
        try {
            return categoryInterface.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todas as categorias", e);
        }
    }

    public Optional<Category> getCategoryById(Long id) {
        try {
            return categoryInterface.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar categoria por ID: " + id, e);
        }
    }

    public List<Category> getCategoriesByName(String name) {
        try {
            Optional<List<Category>> categories = categoryInterface.findByNameContainingIgnoreCase(name);
            return categories.orElse(Collections.emptyList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar categorias por nome: " + name, e);
        }
    }

    public Category saveCategory(Category category) {
        try {
            return categoryInterface.save(category);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar categoria: " + category.getName(), e);
        }
    }

    public Category updateCategoryById(Long id, Category newCategoryData) {
        try {
            Optional<Category> categoryData = categoryInterface.findById(id);
            if (categoryData.isPresent()) {
                Category updatedCategoryData = categoryData.get();
                updatedCategoryData.setName(newCategoryData.getName());
                if (newCategoryData.getBooks() != null) {
                    updatedCategoryData.setBooks(newCategoryData.getBooks());
                }
                return categoryInterface.save(updatedCategoryData);
            } else {
                throw new RuntimeException("Categoria não encontrada com o ID: " + id);
            } 
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar autor por ID: " + id, e);
        }
    }

    public void deleteCategoryById(Long id) {
        try {
            categoryInterface.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar categoria por ID: " + id, e);
        }
    }

}
