package com.legacy.lms.service;

import com.legacy.lms.dto.BorrowingRecord.BorrowBookRequest;
import com.legacy.lms.dto.BorrowingRecord.ReturnBookRequest;
import com.legacy.lms.dto.book.BookCreateDto;
import com.legacy.lms.dto.book.BookUpdateDto;
import com.legacy.lms.entity.Book;
import com.legacy.lms.entity.BorrowingRecord;
import com.legacy.lms.error.exceptions.BadRequestException;
import com.legacy.lms.repository.BookRepository;
import com.legacy.lms.repository.BorrowingRecordRepository;
import com.legacy.lms.util.helper.PaginationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.legacy.lms.util.localization.Tokens;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PatronService patronService;

    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public PaginationResult<Book> getAllBooks(int pageNumber, int pageSize) {

        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<Book> booksPage = bookRepository.findAll(pageable);

        return new PaginationResult<>(booksPage);
    }

    public Book getBook(Long id) {
        var book = bookRepository.findById(id);

        if (book.isEmpty() || book.get().getIsDeleted()) {
            throw new EntityNotFoundException(Tokens.M_BOOK_NOT_FOUND);
        }

        return book.get();
    }

    @Transactional
    public Book createBook(BookCreateDto request) {
        var existingBook = bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor());

        if(existingBook.isPresent())
            throw new BadRequestException(Tokens.BOOK_ALREADY_EXIST);

        var book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublicationYear(request.getPublicationYear());
        book.setIsbn(request.getIsbn());

        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long id, BookUpdateDto request) {
        var book = getBook(id);

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublicationYear(request.getPublicationYear());
        book.setIsbn(request.getIsbn());

        return bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(Long id) {
        var book = getBook(id);

        if (book.getDeletedAt() != null) {
            throw new BadRequestException(Tokens.M_BOOK_ALREADY_DELETED);
        }

        book.delete();

        bookRepository.save(book);
    }


    @Transactional
    public void borrowBook(BorrowBookRequest request) {
        var book = getBook(request.getBookId());
        var patron = patronService.getPatron(request.getPatronId());

        // Check if the book is already borrowed
        var borrowingRecord = borrowingRecordRepository.findBorrowedBook(book.getId());
        if (borrowingRecord != null) {
            throw new BadRequestException("Book is already borrowed");
        }

        // Handle the borrowing of the book by the patron
        var newBorrowingRecord = new BorrowingRecord();
        newBorrowingRecord.setBook(book);
        newBorrowingRecord.setPatron(patron);
        newBorrowingRecord.setBorrowDate(LocalDateTime.now());

        borrowingRecordRepository.save(newBorrowingRecord);
    }

    @Transactional
    public void returnBook(ReturnBookRequest request) {
        var book = getBook(request.getBookId());
        var patron = patronService.getPatron(request.getPatronId());

        // Check if the book is actually borrowed by the patron
        var borrowingRecord = borrowingRecordRepository.findBorrowedBookByPatron(book.getId(), patron.getId());
        if (borrowingRecord == null) {
            throw new BadRequestException("This book is not borrowed by this patron");
        }

        // Handle the return of the book by the patron
        borrowingRecord.setReturnDate(LocalDateTime.now());

        borrowingRecordRepository.save(borrowingRecord);
    }
}
