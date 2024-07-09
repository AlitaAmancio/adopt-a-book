package com.bookAdoption.adoptabook.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookAdoption.adoptabook.entity.Author;
import com.bookAdoption.adoptabook.entity.Book;
import com.bookAdoption.adoptabook.entity.Category;
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

    public List<Book> getAllBooks() {
        try {
            return bookInterface.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar todos os livros", e);
        }
    }

    public Optional<Book> getBookById(Long id) {
        try {
            return bookInterface.findById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livro por ID: " + id, e);
        }
    }

    public List<Book> getBooksByTitle(String title) {
        try {
            Optional<List<Book>> books = bookInterface.findByTitleContainingIgnoreCase(title);     
            return books.orElse(Collections.emptyList());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por título: " + title, e);
        }
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        try {
            Optional<Author> author = authorInterface.findByNameIgnoreCase(authorName);
            if (author.isPresent()) {
                return author.get().getBooks();
            } else {
                return Collections.emptyList();
            }    
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por nome do autor: " + authorName, e);
        }
    }

    public List<Book> getBooksByCategoryName(String categoryName) {
        try {
            Optional<Category> category = categoryInterface.findByNameIgnoreCase(categoryName);
            if (category.isPresent()) {
                return category.get().getBooks();
            } else {
                return Collections.emptyList();
            }  
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar livros por nome da categoria: " + categoryName, e);
        }
    }

    public Book addBook(Book book) {
        try {
            return bookInterface.save(book);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar livro: " + book.getTitle(), e);
        }
    }

    public Book updateBookById(Long id, Book newBookData) {
        try {
            Optional<Book> bookData = bookInterface.findById(id);
            if (bookData.isPresent()) {
                Book updatedBookData = bookData.get();
                updatedBookData.setTitle(newBookData.getTitle());
                updatedBookData.setDescription(newBookData.getDescription());
                updatedBookData.setCoverType(newBookData.getCoverType());
                updatedBookData.setPublicationDate(newBookData.getPublicationDate());
                if (newBookData.getAuthor() != null) {
                    updatedBookData.setAuthor(newBookData.getAuthor());
                }
                if (newBookData.getCategories() != null) {
                    updatedBookData.setCategories(newBookData.getCategories());
                }
                return bookInterface.save(updatedBookData);
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
