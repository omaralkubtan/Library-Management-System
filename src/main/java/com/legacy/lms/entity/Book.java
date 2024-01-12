package com.legacy.lms.entity;


import com.legacy.lms.util.helper.Constants;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Entity(name = "books")
public class Book extends BaseEntity {

    @NotEmpty(message = "Title of book must not be null")
    private String title;

    @NotEmpty(message = "Author of book must not be null")
    private String author;

    @Min(value = 1800, message = "Publication year must be no earlier than 1800")
    @Max(value = 2023, message = "Publication year must be no later than 2023")
    private int publicationYear;

    @Pattern(regexp = Constants.ISBN_REGEXP, message = "Invalid ISBN")
    @NotEmpty(message = "ISBN of book must not be null")
    private String isbn;

}
