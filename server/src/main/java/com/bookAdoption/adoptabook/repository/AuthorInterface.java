package com.bookAdoption.adoptabook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookAdoption.adoptabook.entity.Author;

public interface AuthorInterface extends JpaRepository<Author, Long> {
    Optional<List<Author>> findByNameContainingIgnoreCase(String name);

    Optional<Author> findByNameIgnoreCase(String name);

}
