package com.legacy.lms.dto.BorrowingRecord;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReturnBookRequest {
    @NotNull(message = "Book ID must not be null")
    private Long bookId;

    @NotNull(message = "Patron ID must not be null")
    private Long patronId;
}