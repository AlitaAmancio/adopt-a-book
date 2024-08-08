package com.bookAdoption.adoptabook.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record AuthorDTO(Long id, String name, String biography, Date birthdate, List<Long> bookIds) {
        public AuthorDTO {
        bookIds = (bookIds == null) ? new ArrayList<>() : new ArrayList<>(bookIds);
    }
}
