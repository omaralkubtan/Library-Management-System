package com.legacy.lms.dto.BorrowingRecord;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowingRecordUpdateDto {
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}
