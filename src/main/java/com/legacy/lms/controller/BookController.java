package com.legacy.lms.controller;

import com.legacy.lms.dto.book.BookCreateDto;
import com.legacy.lms.dto.book.BookDetailsDto;
import com.legacy.lms.dto.book.BookListDto;
import com.legacy.lms.dto.book.BookUpdateDto;
import com.legacy.lms.service.BookService;
import com.legacy.lms.util.helper.Constants;
import com.legacy.lms.util.helper.PaginationResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.legacy.lms.rbac.Permissions;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Tag(name = "Books")
@RestController("/books")
@Validated
public class BookController extends ApiController {

    @Autowired
    private BookService bookService;

    @Operation(
            summary = "Get All Books",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping
    @PreAuthorize(AUTH_PREFIX + Permissions.GET_ALL_BOOKS + AUTH_SUFFIX)
    public PaginationResult<BookListDto> getAllBooks(
            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE)
            @PositiveOrZero
            Integer page,

            @RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE)
            @Positive
            Integer pageSize
    )
    {
        var books = bookService.getAllBooks(page, pageSize);
        return books.mapTo(modelMapper, BookListDto.class);
    }

    @Operation(
            summary = "Get Book by ID",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @GetMapping("/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.GET_BOOK_DETAILS + AUTH_SUFFIX)
    public BookDetailsDto getBook(@PathVariable Long id) {
        var book = bookService.getBook(id);
        return modelMapper.map(book, BookDetailsDto.class);
    }

    @Operation(
            summary = "Create New Book",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping
    @PreAuthorize(AUTH_PREFIX + Permissions.CREATE_BOOK + AUTH_SUFFIX)
    public BookDetailsDto createBook(@Valid @RequestBody BookCreateDto request) {
        var book = bookService.createBook(request);
        return modelMapper.map(book, BookDetailsDto.class);
    }

    @Operation(
            summary = "Update Existing Book",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PutMapping("/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.UPDATE_BOOK + AUTH_SUFFIX)
    public BookDetailsDto updateBook(
            @PathVariable("id") Long id,
            @Valid @RequestBody BookUpdateDto request
    ) {
        var book = bookService.updateBook(id, request);
        return modelMapper.map(book, BookDetailsDto.class);
    }

    @Operation(
            summary = "Delete Existing Book",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @DeleteMapping("/{id}")
    @PreAuthorize(AUTH_PREFIX + Permissions.DELETE_BOOK + AUTH_SUFFIX)
    public void deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}
