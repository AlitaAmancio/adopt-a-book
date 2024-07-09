package com.bookAdoption.adoptabook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bookAdoption.adoptabook.entity.Author;
import com.bookAdoption.adoptabook.service.AuthorService;

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
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/list")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(authors, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Optional<Author> authorData = authorService.getAuthorById(id);
        if (authorData.isPresent()) {
            return new ResponseEntity<>(authorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list/{name}")
    public ResponseEntity<List<Author>> getAuthorsByName(@PathVariable String name) {
        List<Author> authorList = authorService.getAuthorsByName(name);
        if (authorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(authorList, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author) {
        Author authorObject = authorService.saveAuthor(author);
        return new ResponseEntity<>(authorObject, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Author> updateAuthorById(@PathVariable Long id, @RequestBody Author newAuthorData) {
        Author updatedAuthor = authorService.updateAuthorById(id, newAuthorData);
        if (updatedAuthor != null) {
            return new ResponseEntity<>(updatedAuthor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
