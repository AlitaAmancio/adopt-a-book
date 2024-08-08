package com.bookAdoption.adoptabook.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookAdoption.adoptabook.dto.AuthorDTO;
import com.bookAdoption.adoptabook.entity.Author;
import com.bookAdoption.adoptabook.entity.Book;
import com.bookAdoption.adoptabook.mapper.AuthorMapper;
import com.bookAdoption.adoptabook.repository.AuthorInterface;
import com.bookAdoption.adoptabook.repository.BookInterface;

@Service
@Transactional
public class AuthorService {

    @Autowired
    private AuthorInterface authorInterface;

    @Autowired
    private BookInterface bookInterface;

    public List<AuthorDTO> getAllAuthors() {
        try {
            return authorInterface.findAll().stream()
                .map(AuthorMapper::toAuthorDTO)
                .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os autores", e);
        }
    }

    public Optional<AuthorDTO> getAuthorById(Long id) {
        try {
            Optional<Author> authorOptional = authorInterface.findById(id);
            return authorOptional.map(AuthorMapper::toAuthorDTO);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar autor por ID: " + id, e);
        }
    }
    

    public List<AuthorDTO> getAuthorsByName(String name) {
        try {
            Optional<List<Author>> authorsOptional = authorInterface.findByNameContainingIgnoreCase(name);
            List<Author> authors = authorsOptional.orElse(Collections.emptyList());
            
            return authors.stream()
                .map(AuthorMapper::toAuthorDTO)
                .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar autores por nome: " + name, e);
        }
    }    

    public AuthorDTO saveAuthor(AuthorDTO authorDto) {
        List<Book> books = bookInterface.findAllById(authorDto.bookIds());
        Author author = AuthorMapper.toAuthorEntity(authorDto, books);
        try {
            Author savedAuthor = authorInterface.save(author);
            return AuthorMapper.toAuthorDTO(savedAuthor);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar autor: " + author.getName(), e);
        }
    }    

    public AuthorDTO updateAuthorById(Long id, AuthorDTO newAuthorDto) {
        try {
            Optional<Author> authorOptional = authorInterface.findById(id);
            if (authorOptional.isPresent()) {
                Author existingAuthor = authorOptional.get();
                existingAuthor.setName(newAuthorDto.name());
                existingAuthor.setBiography(newAuthorDto.biography());
                existingAuthor.setBirthdate(newAuthorDto.birthdate());

                List<Book> books = bookInterface.findAllById(newAuthorDto.bookIds());
                existingAuthor.setBooks(books);

                Author updatedAuthor = authorInterface.save(existingAuthor);
                return AuthorMapper.toAuthorDTO(updatedAuthor);
            } else {
                throw new RuntimeException("Autor não encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar autor por ID: " + id, e);
        }
    }

    public void deleteAuthorById(Long id) {
        try {
            Author author = authorInterface.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor não encontrado com ID: " + id));

            for (Book book : author.getBooks()) {
                book.getAuthors().remove(author);
                bookInterface.save(book);
            }
            authorInterface.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar autor por ID: " + id, e);
        }
    }
}
