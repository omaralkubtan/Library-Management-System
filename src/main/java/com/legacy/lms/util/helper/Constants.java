package com.legacy.lms.util.helper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String PASSWORD_VALIDATION_REGEXP = "^(?=.*[0-9])(?=.*[!@#$%^&*_])[a-zA-Z0-9!@#$%^&*_]{8,}$";

    public static final String ISBN_REGEXP = "^(?=\\D*\\d{10}(?:\\D*\\d{3})?$)[\\d-]+$";

    public static final String DEFAULT_PAGE_SIZE = "20";

    public static final String DEFAULT_PAGE = "0";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String TIME_FORMAT = "HH:mm";

}


