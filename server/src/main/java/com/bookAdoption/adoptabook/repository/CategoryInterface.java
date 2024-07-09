package com.bookAdoption.adoptabook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookAdoption.adoptabook.entity.Category;

public interface CategoryInterface extends JpaRepository<Category, Long> {
    Optional<List<Category>> findByNameContainingIgnoreCase(String name);

    Optional<Category> findByNameIgnoreCase(String name);

}
