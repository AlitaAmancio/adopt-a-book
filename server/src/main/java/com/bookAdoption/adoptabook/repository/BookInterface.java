package com.bookAdoption.adoptabook.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookAdoption.adoptabook.entity.Book;

public interface BookInterface extends JpaRepository<Book, Long> {
    Optional<List<Book>> findByTitleContainingIgnoreCase(String title);

}
