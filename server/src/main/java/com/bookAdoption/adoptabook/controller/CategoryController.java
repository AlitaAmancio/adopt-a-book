package com.bookAdoption.adoptabook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bookAdoption.adoptabook.entity.Category;
import com.bookAdoption.adoptabook.service.CategoryService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> categoryData = categoryService.getCategoryById(id);
        return categoryData.map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/list/{name}")
    public ResponseEntity<List<Category>> getCategoriesByName(@PathVariable String name) {
        List<Category> categoriesList = categoryService.getCategoriesByName(name);
        if (categoriesList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(categoriesList, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category) {
        Category categoryObject = categoryService.saveCategory(category);
        return new ResponseEntity<>(categoryObject, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Category> updateCategoryById(@PathVariable Long id, @RequestBody Category newCategoryData) {
        Category updatedCategory = categoryService.updateCategoryById(id, newCategoryData);
        return updatedCategory != null ? new ResponseEntity<>(updatedCategory, HttpStatus.OK)
                                       : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
