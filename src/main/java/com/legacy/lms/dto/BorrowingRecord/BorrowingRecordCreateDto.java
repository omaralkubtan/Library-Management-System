package com.legacy.lms.dto.BorrowingRecord;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class BorrowingRecordCreateDto {

    @NotNull(message = "Please Specify the patron")
    private Long patronId;

    @NotNull(message = "Please Specify the book")
    private Long bookId;

    @NotNull(message = "Please Specify the date of borrowing")
    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;
}