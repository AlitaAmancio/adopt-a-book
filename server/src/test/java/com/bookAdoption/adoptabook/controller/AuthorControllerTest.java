package com.bookAdoption.adoptabook.controller;

import com.bookAdoption.adoptabook.entity.Author;
import com.bookAdoption.adoptabook.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given;

@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Test
    void testDeleteAuthorById() throws Exception {
        Mockito.doNothing().when(authorService).deleteAuthorById(1L);

        mockMvc.perform(delete("/author/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllAuthors() throws Exception {
        given(authorService.getAllAuthors()).willReturn(Collections.emptyList());

        mockMvc.perform(get("/author/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetAuthorById() throws Exception {
        Author author = new Author(1L, "Joao John", "Biografia", new Date());
        given(authorService.getAuthorById(1L)).willReturn(Optional.of(author));

        mockMvc.perform(get("/author/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Joao John\",\"biography\":\"Biografia\"}"));
    }

    @Test
    void testGetAuthorsByName() throws Exception {
        Author author1 = new Author(1L, "Joao John", "Biografia", new Date());
        Author author2 = new Author(2L, "Joana Jane", "Outra Biografia", new Date());
        List<Author> authors = Arrays.asList(author1, author2);
        given(authorService.getAuthorsByName("Joao")).willReturn(authors);
    
        mockMvc.perform(get("/author/list/Joao")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1,\"name\":\"Joao John\",\"biography\":\"Biografia\"},{\"id\":2,\"name\":\"Joana Jane\",\"biography\":\"Outra Biografia\"}]"));
    }
    

    @Test
    void testSaveAuthor() throws Exception {
        Author author = new Author(1L, "Joao John", "Biografia", new Date());
        given(authorService.saveAuthor(Mockito.any(Author.class))).willReturn(author);

        mockMvc.perform(post("/author/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Joao John\",\"biography\":\"Biografia\",\"birthdate\":\"2024-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Joao John\",\"biography\":\"Biografia\"}"));
    }

    @Test
    void testUpdateAuthorById() throws Exception {
        Author updatedAuthor = new Author(1L, "Joana Jane", "Biografia Nova", new Date());
        given(authorService.updateAuthorById(Mockito.eq(1L), Mockito.any(Author.class))).willReturn(updatedAuthor);

        mockMvc.perform(put("/author/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Joao John\",\"biography\":\"Biografia Antiga\",\"birthdate\":\"2024-01-01\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1,\"name\":\"Joana Jane\",\"biography\":\"Biografia Nova\"}"));
    }

}

