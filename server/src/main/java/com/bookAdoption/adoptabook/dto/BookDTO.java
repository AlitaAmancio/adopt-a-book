package com.bookAdoption.adoptabook.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public record BookDTO(Long id, String title, String description, String coverType, Date publicationDate, List<Long> authorIds, List<Long> categoryIds) {
    public BookDTO {
        authorIds = (authorIds == null) ? new ArrayList<>() : new ArrayList<>(authorIds);
        categoryIds = (categoryIds == null) ? new ArrayList<>() : new ArrayList<>(categoryIds);
    }
}
