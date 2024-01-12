package com.legacy.lms.controller;

import com.legacy.lms.dto.BorrowingRecord.BorrowBookRequest;
import com.legacy.lms.dto.BorrowingRecord.ReturnBookRequest;
import com.legacy.lms.rbac.Permissions;
import com.legacy.lms.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Borrowing Record")
@RestController
@RequestMapping("")
public class BorrowingRecordController extends ApiController {

    @Autowired
    private BookService bookService;

    @Operation(
            summary = "Allow a patron to borrow a book",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @PreAuthorize(AUTH_PREFIX + Permissions.BORROW_BOOK + AUTH_SUFFIX)
    public void borrowBook(@PathVariable("bookId") Long bookId, @PathVariable("patronId") Long patronId) {
        var request = new BorrowBookRequest();
        request.setBookId(bookId);
        request.setPatronId(patronId);
        bookService.borrowBook(request);
    }

    @Operation(
            summary = "Record the return of a borrowed book by a patron",
            parameters = @Parameter(name = "Accept-Language", in = ParameterIn.HEADER, example = "en")
    )
    @PutMapping("/return/{bookId}/patron/{patronId}")
    @PreAuthorize(AUTH_PREFIX + Permissions.RETURN_BOOK + AUTH_SUFFIX)
    public void returnBook(@PathVariable("bookId") Long bookId, @PathVariable("patronId") Long patronId) {
        var request = new ReturnBookRequest();
        request.setBookId(bookId);
        request.setPatronId(patronId);
        bookService.returnBook(request);
    }
}
