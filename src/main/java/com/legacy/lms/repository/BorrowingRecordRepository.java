package com.legacy.lms.repository;

import com.legacy.lms.entity.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    @Query(value = "SELECT * FROM borrowing_records " +
            " WHERE book_id = :bookId " +
            " AND return_date IS NULL " +
            " AND deleted_at IS NULL", nativeQuery = true)
    Optional<BorrowingRecord> findBorrowedBook(long bookId);

    @Query(value = "SELECT * FROM borrowing_records " +
            " WHERE book_id = :bookId " +
            " AND patron_id = :patronId " +
            " AND return_date IS NULL " +
            " AND deleted_at IS NULL", nativeQuery = true)
    Optional<BorrowingRecord> findBorrowedBookByPatron(long bookId, long patronId);
}
