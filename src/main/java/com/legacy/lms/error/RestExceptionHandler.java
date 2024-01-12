package com.legacy.lms.error;

import com.legacy.lms.error.exceptions.BadRequestException;
import com.legacy.lms.error.exceptions.ForbiddenException;
import com.legacy.lms.util.localization.Tokens;
import com.legacy.lms.util.localization.Translator;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;

import static org.springframework.http.HttpStatus.*;

@SuppressWarnings("NullableProblems")
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private Translator translator;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        var message = translator.t(Tokens.E_MALFORMED_JSON_REQUEST);
        var apiError = new ApiError(BAD_REQUEST, message);

        return buildResponseEntity(apiError);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        var message = translator.t(Tokens.E_INVALID_REQUEST_DATA);

        System.out.println(message);

        var subErrors = new ArrayList<ApiSubError>();

        for (var fieldError : ex.getFieldErrors()) {
            var name = fieldError.getField();
            var msg = translator.t(fieldError.getDefaultMessage());

            var subError = new ApiSubError(name, msg);

            subErrors.add(subError);
        }

        var apiError = new ApiError(UNPROCESSABLE_ENTITY, message, subErrors);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
        var message = translator.t(Tokens.E_INVALID_REQUEST_PARAMS);

        var subErrors = new ArrayList<ApiSubError>();

        for (var violation : ex.getConstraintViolations()) {
            var name = violation.getPropertyPath().toString();
            var msg = translator.t(violation.getMessage());

            var subError = new ApiSubError(name, msg);

            subErrors.add(subError);
        }

        var apiError = new ApiError(BAD_REQUEST, message, subErrors);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        var message = translator.t(ex.getMessage());
        var apiError = new ApiError(NOT_FOUND, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntityExistsException.class)
    protected ResponseEntity<Object> handleEntityExists(EntityExistsException ex) {
        var message = translator.t(ex.getMessage());
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> handleBadCredentials(BadCredentialsException ex) {
        var message = translator.t(Tokens.E_BAD_CREDS);
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex, WebRequest request) {
        var message = translator.t(Tokens.E_ACCESS_DENIED);
        var apiError = new ApiError(FORBIDDEN, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequest(BadRequestException ex) {
        var message = translator.t(ex.getMessage());
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<Object> handleForbidden(ForbiddenException ex) {
        var message = translator.t(ex.getMessage());
        var apiError = new ApiError(FORBIDDEN, message);
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(SizeLimitExceededException.class)
    protected ResponseEntity<Object> handleSizeLimitExceeded(SizeLimitExceededException ex) {
        var message = translator.t(Tokens.E_SIZE_LIMIT_EXCEEDED);
        var apiError = new ApiError(BAD_REQUEST, message);
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
