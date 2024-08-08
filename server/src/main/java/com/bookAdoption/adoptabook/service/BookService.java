package com.bookAdoption.adoptabook.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookAdoption.adoptabook.dto.BookDTO;
import com.bookAdoption.adoptabook.entity.Author;
import com.bookAdoption.adoptabook.entity.Book;
import com.bookAdoption.adoptabook.entity.Category;
import com.bookAdoption.adoptabook.mapper.BookMapper;
import com.bookAdoption.adoptabook.repository.AuthorInterface;
import com.bookAdoption.adoptabook.repository.BookInterface;
import com.bookAdoption.adoptabook.repository.CategoryInterface;

@Service
@Transactional
public class BookService {

    @Autowired
    private BookInterface bookInterface;

    @Autowired
    private AuthorInterface authorInterface;

    @Autowired
    private CategoryInterface categoryInterface;

    public List<BookDTO> getAllBooks() {
        try {
            return bookInterface.findAll().stream()
                .map(BookMapper::toBookDTO)
                .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os livros", e);
        }
    }

    public Optional<BookDTO> getBookById(Long id) {
        try {
            Optional<Book> bookOptional = bookInterface.findById(id);
            return bookOptional.map(BookMapper::toBookDTO);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livro por ID: " + id, e);
        }
    }

    public List<BookDTO> getBooksByTitle(String title) {
        try {
            Optional<List<Book>> booksOptional = bookInterface.findByTitleContainingIgnoreCase(title);
            List<Book> books = booksOptional.orElse(Collections.emptyList());

            return books.stream()
                .map(BookMapper::toBookDTO)
                .toList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por título: " + title, e);
        }
    }

    public List<BookDTO> getBooksByAuthorName(String authorName) {
        try {
            Optional<Author> authorOptional = authorInterface.findByNameIgnoreCase(authorName);
            if (authorOptional.isPresent()) {
                return authorOptional.get().getBooks().stream()
                    .map(BookMapper::toBookDTO)
                    .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por nome do autor: " + authorName, e);
        }
    }

    public List<BookDTO> getBooksByCategoryName(String categoryName) {
        try {
            Optional<Category> categoryOptional = categoryInterface.findByNameIgnoreCase(categoryName);
            if (categoryOptional.isPresent()) {
                return categoryOptional.get().getBooks().stream()
                    .map(BookMapper::toBookDTO)
                    .collect(Collectors.toList());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por nome da categoria: " + categoryName, e);
        }
    }

    public BookDTO addBook(BookDTO bookDto) {
        try {
            List<Author> authors = bookDto.authorIds().stream()
                .map(authorId -> authorInterface.findById(authorId)
                    .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + authorId)))
                .toList();

            List<Category> categories = bookDto.categoryIds().stream()
                .map(categoryId -> categoryInterface.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + categoryId)))
                .toList();

            Book book = BookMapper.toBookEntity(bookDto, authors, categories);
            Book savedBook = bookInterface.save(book);
            return BookMapper.toBookDTO(savedBook);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar livro: " + bookDto.title(), e);
        }
    }

    public BookDTO updateBookById(Long id, BookDTO newBookDto) {
        try {
            Optional<Book> bookOptional = bookInterface.findById(id);
            if (bookOptional.isPresent()) {
                Book existingBook = bookOptional.get();
                existingBook.setTitle(newBookDto.title());
                existingBook.setDescription(newBookDto.description());
                existingBook.setCoverType(newBookDto.coverType());
                existingBook.setPublicationDate(newBookDto.publicationDate());
    
                existingBook.getAuthors().clear();
                existingBook.getCategories().clear();
    
                List<Author> authors = newBookDto.authorIds().stream()
                    .map(authorId -> authorInterface.findById(authorId)
                        .orElseThrow(() -> new RuntimeException("Autor não encontrado com o ID: " + authorId)))
                    .collect(Collectors.toList());
                existingBook.getAuthors().addAll(authors);
    
                List<Category> categories = newBookDto.categoryIds().stream()
                    .map(categoryId -> categoryInterface.findById(categoryId)
                        .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID: " + categoryId)))
                    .collect(Collectors.toList());
                existingBook.getCategories().addAll(categories);
    
                Book updatedBook = bookInterface.save(existingBook);
                return BookMapper.toBookDTO(updatedBook);
            } else {
                throw new RuntimeException("Livro não encontrado com o ID: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar livro por ID: " + id, e);
        }
    }

    public void deleteBookById(Long id) {
        try {
            bookInterface.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar livro por ID: " + id, e);
        }
    }
}
