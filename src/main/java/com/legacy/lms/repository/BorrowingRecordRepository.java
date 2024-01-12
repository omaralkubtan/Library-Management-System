package com.legacy.lms.repository;

import com.legacy.lms.entity.Book;
import com.legacy.lms.entity.BorrowingRecord;
import com.legacy.lms.entity.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    @Query(value = "SELECT * FROM borrowing_records " +
            " WHERE book_id = :bookId " +
            " AND return_date IS NULL " +
            " AND deleted_at IS NULL", nativeQuery = true)
    BorrowingRecord findBorrowedBook(long bookId);

    @Query(value = "SELECT * FROM borrowing_records " +
            " WHERE book_id = :bookId " +
            " AND patron_id = :patronId " +
            " AND return_date IS NULL " +
            " AND deleted_at IS NULL", nativeQuery = true)
    BorrowingRecord findBorrowedBookByPatron(long bookId, long patronId);
}
