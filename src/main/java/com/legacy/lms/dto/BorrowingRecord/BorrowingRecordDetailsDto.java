package com.legacy.lms.dto.BorrowingRecord;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowingRecordDetailsDto {
    private Long id;
    private Long patronId;
    private Long bookId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}