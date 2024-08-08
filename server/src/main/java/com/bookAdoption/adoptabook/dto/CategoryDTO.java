package com.bookAdoption.adoptabook.dto;

import java.util.ArrayList;
import java.util.List;

public record CategoryDTO(Long id, String name, List<Long> bookIds) {
    public CategoryDTO {
        bookIds = (bookIds == null) ? new ArrayList<>() : new ArrayList<>(bookIds);
    }
}
