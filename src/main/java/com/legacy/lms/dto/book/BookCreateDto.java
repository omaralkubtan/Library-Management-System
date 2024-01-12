package com.legacy.lms.dto.book;

import com.legacy.lms.util.helper.Constants;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class BookCreateDto {

    @NotEmpty(message = "Please enter the title of the book")
    private String title;

    @NotEmpty(message = "Please enter the author name of the book")
    private String author;

    @Min(value = 1800, message = "Publication year must be no earlier than 1800")
    @Max(value = 2023, message = "Publication year must be no later than 2023")
    private int publicationYear;

    @NotEmpty(message = "PLease enter the ISBN number of the book")
    @Pattern(regexp = Constants.ISBN_REGEXP, message = "Invalid ISBN please enter 13 digit, ex: 1234567890123")
    private String isbn;
}