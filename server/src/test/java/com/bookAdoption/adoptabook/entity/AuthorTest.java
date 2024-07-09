package com.bookAdoption.adoptabook.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

class AuthorTest {
    
    @Test
    void testAuthorCreation() {
        Date birthdate = new Date();
        Author author = new Author(1L, "Joao John", "Biografia", birthdate);

        assertEquals(1L, author.getId());
        assertEquals("Joao John", author.getName());
        assertEquals("Biografia", author.getBiography());
        assertEquals(birthdate, author.getBirthdate());
    }

    @Test
    void testSetBiography() {
        Author author = new Author();
        author.setBiography("Biografia Nova");

        assertEquals("Biografia Nova", author.getBiography());
        
    }

    @Test
    void testSetBirthdate() {
        Author author = new Author();
        author.setBirthdate(new Date());

        assertEquals(new Date(), author.getBirthdate());
    }

    @Test
    public void testSetName() {
        Author author = new Author();
        author.setName("Joana Jane");

        assertEquals("Joana Jane", author.getName());
    }
}


