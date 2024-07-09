package com.bookAdoption.adoptabook.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookAdoption.adoptabook.entity.Author;
import com.bookAdoption.adoptabook.repository.AuthorInterface;



@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorInterface authorInterface;

    public List<Author> getAllAuthors() {
        try {
            return authorInterface.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os autores", e);
        }
    }

    public Optional<Author> getAuthorById(Long id) {
        try {
            return authorInterface.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar autor por ID: " + id, e);
        }
    }

    public List<Author> getAuthorsByName(String name) {
        try {
            Optional<List<Author>> authors = authorInterface.findByNameContainingIgnoreCase(name);
            return authors.orElse(Collections.emptyList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar autores por nome: " + name, e);
        }
    }

    public Author saveAuthor(Author author) {
        try {
            return authorInterface.save(author);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar autor: " + author.getName(), e);
        }
    }

    public Author updateAuthorById(Long id, Author newAuthorData) {
        try {
            Optional<Author> authorData = authorInterface.findById(id);
            if (authorData.isPresent()) {
                Author updatedAuthorData = authorData.get();
                updatedAuthorData.setName(newAuthorData.getName());
                updatedAuthorData.setBiography(newAuthorData.getBiography());
                updatedAuthorData.setBirthdate(newAuthorData.getBirthdate());
                if (newAuthorData.getBooks() != null) {
                    updatedAuthorData.setBooks(newAuthorData.getBooks());
                }
                return authorInterface.save(updatedAuthorData);
            } else {
                throw new RuntimeException("Autor não encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar autor por ID: " + id, e);
        }
    }

    public void deleteAuthorById(Long id) {
        try {
            authorInterface.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar autor por ID: " + id, e);
        }
    }
    
}
