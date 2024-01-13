package service;

import com.legacy.lms.dto.BorrowingRecord.BorrowBookRequest;
import com.legacy.lms.dto.BorrowingRecord.ReturnBookRequest;
import com.legacy.lms.entity.Book;
import com.legacy.lms.entity.BorrowingRecord;
import com.legacy.lms.entity.Patron;
import com.legacy.lms.error.exceptions.BadRequestException;
import com.legacy.lms.repository.BookRepository;
import com.legacy.lms.repository.BorrowingRecordRepository;
import com.legacy.lms.repository.PatronRepository;
import com.legacy.lms.service.BookService;
import com.legacy.lms.service.PatronService;
import org.junit.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class BorrowBookTest {


    private PatronRepository injectPatronRepository(PatronService patronService) throws NoSuchFieldException, IllegalAccessException {
        PatronRepository patronRepository = mock(PatronRepository.class);

        // Use reflection to set the private field
        Field field = patronService.getClass().getDeclaredField("patronRepository");
        field.setAccessible(true);
        field.set(patronService, patronRepository);

        return patronRepository;
    }

    private PatronService injectPatronService(BookService bookService) throws NoSuchFieldException, IllegalAccessException {
        PatronService patronService = mock(PatronService.class);

        // Use reflection to set the private field
        Field field = bookService.getClass().getDeclaredField("patronService");
        field.setAccessible(true);
        field.set(bookService, patronService);

        return patronService;
    }

    private BorrowingRecordRepository injectBorrowingRecordRepository(BookService bookService) throws NoSuchFieldException, IllegalAccessException {
        BorrowingRecordRepository borrowingRecordRepository = mock(BorrowingRecordRepository.class);

        // Use reflection to set the private field
        Field field = bookService.getClass().getDeclaredField("borrowingRecordRepository");
        field.setAccessible(true);
        field.set(bookService, borrowingRecordRepository);

        return borrowingRecordRepository;
    }

    private BookRepository injectBookRepository(BookService bookService) throws NoSuchFieldException, IllegalAccessException {
        BookRepository bookRepository = mock((BookRepository.class));

        Field field = bookService.getClass().getDeclaredField("bookRepository");
        field.setAccessible(true);
        field.set(bookService, bookRepository);

        return bookRepository;
    }


    // Borrowing a book that is not borrowed
    @Test
    public void testBorrowBookAvailable() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BorrowBookRequest request = new BorrowBookRequest();
        request.setBookId(1L);
        request.setPatronId(1L);

        BookService bookService = new BookService();
        injectPatronService(bookService);
        BookRepository bookRepository = injectBookRepository(bookService);
        BorrowingRecordRepository borrowingRecordRepository = injectBorrowingRecordRepository(bookService);
        PatronRepository patronRepository = mock(PatronRepository.class);


        Book book = new Book();
        book.setId(1L);

        Patron patron = new Patron();
        patron.setId(1L);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.findBorrowedBook(1L)).thenReturn(Optional.empty());


        // Act
        bookService.borrowBook(request);

        // Assert
        verify(borrowingRecordRepository, Mockito.times(1)).save(Mockito.any(BorrowingRecord.class));
    }

    // Borrowing a book that already has been borrowed
    @Test
    public void testBorrowBookAlreadyBorrowed() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BorrowBookRequest request = new BorrowBookRequest();
        request.setBookId(1L);
        request.setPatronId(1L);

        BookService bookService = new BookService();
        injectPatronService(bookService);
        BookRepository bookRepository = injectBookRepository(bookService);
        BorrowingRecordRepository borrowingRecordRepository = injectBorrowingRecordRepository(bookService);
        PatronRepository patronRepository = mock(PatronRepository.class);

        Book book = new Book();
        book.setId(1L);

        Patron patron = new Patron();
        patron.setId(1L);

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);
        borrowingRecord.setReturnDate(LocalDateTime.now());

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.findBorrowedBook(1L)).thenReturn(Optional.of(borrowingRecord));

        // Act and Assert
        assertThrows(BadRequestException.class, () -> {
            bookService.borrowBook(request);
        });
    }

    @Test
    public void test_book_returned_successfully() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        ReturnBookRequest request = new ReturnBookRequest();
        request.setBookId(1L);
        request.setPatronId(1L);

        BookService bookService = new BookService();
        PatronService patronService = injectPatronService(bookService);
        BookRepository bookRepository = injectBookRepository(bookService);
        BorrowingRecordRepository borrowingRecordRepository = injectBorrowingRecordRepository(bookService);
        PatronRepository patronRepository = mock(PatronRepository.class);

        Book book = new Book();
        book.setId(1L);

        Patron patron = new Patron();
        patron.setId(1L);

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setBook(book);
        borrowingRecord.setPatron(patron);

        // Define behaviors of the methods that will be called
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));
        when(borrowingRecordRepository.findBorrowedBookByPatron(1L, 1L)).thenReturn(Optional.of(borrowingRecord));

        when(patronService.getPatron(1L)).thenReturn(patron);

        // Act
        bookService.returnBook(request);

        // Assert
        verify(borrowingRecordRepository, times(1)).save(borrowingRecord);
    }
}