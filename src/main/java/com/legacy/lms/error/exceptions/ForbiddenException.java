package com.legacy.lms.error.exceptions;

import com.legacy.lms.util.localization.Tokens;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() { super(Tokens.E_ACCESS_DENIED);}
}
