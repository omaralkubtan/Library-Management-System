import com.legacy.lms.controller.BorrowingRecordController;
import com.legacy.lms.dto.BorrowingRecord.BorrowBookRequest;
import com.legacy.lms.dto.BorrowingRecord.ReturnBookRequest;
import com.legacy.lms.error.exceptions.BadRequestException;
import com.legacy.lms.service.BookService;
import com.legacy.lms.util.localization.Tokens;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class BorrowingRecordControllerTest {


    // The 'borrowBook' method should successfully borrow a book for a patron when given valid book and patron IDs.
    @Test
    public void test_borrowBook_success() throws IllegalAccessException, NoSuchFieldException {
        // Arrange
        BorrowingRecordController controller = new BorrowingRecordController();
        BookService bookService = mock(BookService.class);
//        controller.setBookService(bookService);

        // Use reflection to set the private field
        Field field = controller.getClass().getDeclaredField("bookService");
        field.setAccessible(true);
        field.set(controller, bookService);

        Long bookId = 1L;
        Long patronId = 1L;
    
        // Act
        controller.borrowBook(bookId, patronId);
    
        // Assert
        verify(bookService, times(1)).borrowBook(any(BorrowBookRequest.class));
    }

    // The 'returnBook' method should successfully record the return of a borrowed book by a patron when given valid book and patron IDs.
    @Test
    public void test_returnBook_success() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BorrowingRecordController controller = new BorrowingRecordController();
        BookService bookService = mock(BookService.class);

        // Use reflection to set the private field
        Field field = controller.getClass().getDeclaredField("bookService");
        field.setAccessible(true);
        field.set(controller, bookService);

        Long bookId = 1L;
        Long patronId = 1L;
    
        // Act
        controller.returnBook(bookId, patronId);
    
        // Assert
        verify(bookService, times(1)).returnBook(any(ReturnBookRequest.class));
    }

    // The 'borrowBook' method should throw a 'BadRequestException' when the book is already borrowed.
    @Test
    public void test_borrowBook_bookAlreadyBorrowed() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BorrowingRecordController controller = new BorrowingRecordController();
        BookService bookService = mock(BookService.class);

        // Use reflection to set the private field
        Field field = controller.getClass().getDeclaredField("bookService");
        field.setAccessible(true);
        field.set(controller, bookService);

        Long bookId = 1L;
        Long patronId = 1L;
        doThrow(new BadRequestException("Book is already borrowed")).when(bookService).borrowBook(any(BorrowBookRequest.class));
    
        // Act & Assert
        assertThrows(BadRequestException.class, () -> controller.borrowBook(bookId, patronId));
    }

    // The 'borrowBook' method should throw an 'EntityNotFoundException' when the book ID is invalid.
    @Test
    public void test_borrowBook_invalidBookId() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BorrowingRecordController controller = new BorrowingRecordController();
        BookService bookService = mock(BookService.class);

        // Use reflection to set the private field
        Field field = controller.getClass().getDeclaredField("bookService");
        field.setAccessible(true);
        field.set(controller, bookService);

        Long bookId = 1L;
        Long patronId = 1L;
        doThrow(new EntityNotFoundException(Tokens.M_BOOK_NOT_FOUND)).when(bookService).borrowBook(any(BorrowBookRequest.class));
    
        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> controller.borrowBook(bookId, patronId));
    }

    // The 'borrowBook' method should throw an 'EntityNotFoundException' when the patron ID is invalid.
    @Test
    public void test_borrowBook_invalidPatronId() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BorrowingRecordController controller = new BorrowingRecordController();
        BookService bookService = mock(BookService.class);

        // Use reflection to set the private field
        Field field = controller.getClass().getDeclaredField("bookService");
        field.setAccessible(true);
        field.set(controller, bookService);

        Long bookId = 1L;
        Long patronId = 1L;
        doThrow(new EntityNotFoundException(Tokens.M_PATRON_NOT_FOUND)).when(bookService).borrowBook(any(BorrowBookRequest.class));
    
        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> controller.borrowBook(bookId, patronId));
    }

    // The 'returnBook' method should throw an 'EntityNotFoundException' when the book ID is invalid.
    @Test
    public void test_returnBook_invalidBookId() throws IllegalAccessException, NoSuchFieldException {
        // Arrange
        BorrowingRecordController controller = new BorrowingRecordController();
        BookService bookService = mock(BookService.class);

        // Use reflection to set the private field
        Field field = controller.getClass().getDeclaredField("bookService");
        field.setAccessible(true);
        field.set(controller, bookService);

        Long bookId = 1L;
        Long patronId = 1L;
        doThrow(new EntityNotFoundException(Tokens.M_BOOK_NOT_FOUND)).when(bookService).returnBook(any(ReturnBookRequest.class));
    
        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> controller.returnBook(bookId, patronId));
    }

}