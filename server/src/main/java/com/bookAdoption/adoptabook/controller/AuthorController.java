package com.bookAdoption.adoptabook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.bookAdoption.adoptabook.dto.AuthorDTO;
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
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(authors, HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        Optional<AuthorDTO> authorData = authorService.getAuthorById(id);
        if (authorData.isPresent()) {
            return new ResponseEntity<>(authorData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list/{name}")
    public ResponseEntity<List<AuthorDTO>> getAuthorsByName(@PathVariable String name) {
        List<AuthorDTO> authorList = authorService.getAuthorsByName(name);
        if (authorList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(authorList, HttpStatus.OK);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<AuthorDTO> saveAuthor(@RequestBody AuthorDTO authorDto) {
        AuthorDTO savedAuthorDto = authorService.saveAuthor(authorDto);
        return new ResponseEntity<>(savedAuthorDto, HttpStatus.OK);
    }
    

    @PutMapping("/update/{id}")
    public ResponseEntity<AuthorDTO> updateAuthorById(@PathVariable Long id, @RequestBody AuthorDTO newAuthorDto) {
        AuthorDTO updatedAuthorDto = authorService.updateAuthorById(id, newAuthorDto);
        if (updatedAuthorDto != null) {
            return new ResponseEntity<>(updatedAuthorDto, HttpStatus.OK);
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
