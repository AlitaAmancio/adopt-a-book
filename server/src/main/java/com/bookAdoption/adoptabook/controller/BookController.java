package com.bookAdoption.adoptabook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bookAdoption.adoptabook.entity.Book;
import com.bookAdoption.adoptabook.service.BookService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> bookData = bookService.getBookById(id);
        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list/title/{title}")
    public ResponseEntity<List<Book>> getBookByTitle(@PathVariable String title) {
        List<Book> bookList = bookService.getBooksByTitle(title);
        if (bookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }
    }

    @GetMapping("/list/author/{authorName}")
    public ResponseEntity<List<Book>> getBooksByAuthorName(@PathVariable String authorName) {
        List<Book> bookList = bookService.getBooksByAuthorName(authorName);
        if (bookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }
    }

    @GetMapping("/list/category/{categoryName}")
    public ResponseEntity<List<Book>> getBooksByCategoryName(@PathVariable String categoryName) {
        List<Book> bookList = bookService.getBooksByCategoryName(categoryName);
        if (bookList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book bookObject = bookService.addBook(book);
        return new ResponseEntity<>(bookObject, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book newBookData) {
        Book updatedBook = bookService.updateBookById(id, newBookData);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
