package com.legacy.lms.error.exceptions;

import com.legacy.lms.util.localization.Tokens;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super(Tokens.E_BAD_REQUEST);
    }
    public BadRequestException(String message) {
        super(message);
    }
}
