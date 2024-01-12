package com.legacy.lms.dto.book;

import lombok.Data;

@Data
public class BookDetailsDto {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
}