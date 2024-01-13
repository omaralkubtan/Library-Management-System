package service;


import com.legacy.lms.dto.book.BookCreateDto;
import com.legacy.lms.entity.Book;
import com.legacy.lms.error.exceptions.BadRequestException;
import com.legacy.lms.repository.BookRepository;
import com.legacy.lms.service.BookService;
import com.legacy.lms.util.helper.PaginationResult;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BookServiceTest {

    private BookRepository injectPrivateRepository(BookService bookService) throws NoSuchFieldException, IllegalAccessException {
        BookRepository bookRepository = mock(BookRepository.class);

        // Use reflection to set the private field
        Field field = bookService.getClass().getDeclaredField("bookRepository");
        field.setAccessible(true);
        field.set(bookService, bookRepository);

        return bookRepository;
    }


    // getAllBooks returns a PaginationResult of books when given a valid page number and page size
    @Test
    public void test_getAllBooks_returns_PaginationResult() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BookService bookService = new BookService();
        BookRepository bookRepository = injectPrivateRepository(bookService);

        int pageNumber = 1;
        int pageSize = 10;


        @SuppressWarnings("unchecked")
        // Create a mock Page
        Page<Book> mockPage = mock(Page.class);

        // Create a mock Pageable
        Pageable mockPageable = PageRequest.of(pageNumber, pageSize);

        // Define the behavior of bookRepository.findAll(pageable)
        when(bookRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        // Define the behavior of mockPage.getPageable()
        when(mockPage.getPageable()).thenReturn(mockPageable);

        // Act
        PaginationResult<Book> result = bookService.getAllBooks(pageNumber, pageSize);

        // Assert
        assertNotNull(result);
        assertEquals(pageNumber, result.getPage());
        assertEquals(pageSize, result.getPageSize());
        assertNotNull(result.getData());
    }

    // getBook returns a book when given a valid book id
    @Test
    public void test_getBook_returns_book_with_valid_id() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BookService bookService = new BookService();
        BookRepository bookRepository = injectPrivateRepository(bookService);

        Long bookId = 1L;

        // Create a mock Book
        Book mockBook = mock(Book.class);
        when(mockBook.getId()).thenReturn(bookId);
        when(mockBook.getIsDeleted()).thenReturn(false);

        // Define the behavior of bookRepository.findById(id)
        Optional<Book> optionalBook = Optional.of(mockBook);
        when(bookRepository.findById(bookId)).thenReturn(optionalBook);

        // Act
        Book result = bookService.getBook(bookId);

        // Assert
        assertNotNull(result);
        assertEquals(bookId, result.getId());
    }

    // createBook creates a book when given valid book data
    @Test
    public void test_createBook_creates_book_with_valid_data() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BookService bookService = new BookService();
        BookRepository bookRepository = injectPrivateRepository(bookService);

        BookCreateDto request = new BookCreateDto();
        request.setTitle("Test Book");
        request.setAuthor("Test Author");
        request.setPublicationYear(2022);
        request.setIsbn("1234567890123");

        // Create a mock Book
        Book mockBook = mock(Book.class);
        when(mockBook.getTitle()).thenReturn(request.getTitle());
        when(mockBook.getAuthor()).thenReturn(request.getAuthor());
        when(mockBook.getPublicationYear()).thenReturn(request.getPublicationYear());
        when(mockBook.getIsbn()).thenReturn(request.getIsbn());

        // Define the behavior of bookRepository.save(book)
        when(bookRepository.save(any(Book.class))).thenReturn(mockBook);

        // Act
        Book result = bookService.createBook(request);

        // Assert
        assertNotNull(result);
        assertEquals(request.getTitle(), result.getTitle());
        assertEquals(request.getAuthor(), result.getAuthor());
        assertEquals(request.getPublicationYear(), result.getPublicationYear());
        assertEquals(request.getIsbn(), result.getIsbn());
    }

    // getBook throws an EntityNotFoundException when given an invalid book id
    @Test
    public void test_getBook_throws_EntityNotFoundException_with_invalid_id() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BookService bookService = new BookService();
        injectPrivateRepository(bookService);

        Long invalidBookId = 100L;

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> bookService.getBook(invalidBookId));
    }

    // getBook throws an EntityNotFoundException when given a valid book id for a deleted book
    @Test
    public void test_getBook_throws_EntityNotFoundException_with_deleted_book_id() throws IllegalAccessException, NoSuchFieldException {
        // Arrange
        BookService bookService = new BookService();
        injectPrivateRepository(bookService);

        Long deletedBookId = 2L;

        // Act & Assert
        assertThrows(EntityNotFoundException.class, () -> bookService.getBook(deletedBookId));
    }

    // createBook throws a BadRequestException when given book data for a book that already exists
    @Test
    public void test_createBook_throws_BadRequestException_with_existing_book_data() throws NoSuchFieldException, IllegalAccessException {
        // Arrange
        BookService bookService = new BookService();
        BookRepository bookRepository = injectPrivateRepository(bookService);

        BookCreateDto request = new BookCreateDto();
        request.setTitle("Existing Book");
        request.setAuthor("Existing Author");
        request.setPublicationYear(2021);
        request.setIsbn("9876543210987");

        // Create a mock Book
        Book mockBook = mock(Book.class);

        // Define the behavior of bookRepository.findByTitleAndAuthor(title, author)
        Optional<Book> optionalBook = Optional.of(mockBook);
        when(bookRepository.findByTitleAndAuthor(request.getTitle(), request.getAuthor())).thenReturn(optionalBook);

        // Act & Assert
        assertThrows(BadRequestException.class, () -> bookService.createBook(request));
    }

}