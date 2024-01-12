package com.legacy.lms.rbac;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@SuppressWarnings("unused")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Permissions {

    // Roles & Permissions
    public static final String GET_ALL_PERMISSIONS = "get-all-permissions";
    public static final String CREATE_ROLE = "create-role";
    public static final String UPDATE_ROLE = "update-role";
    public static final String DELETE_ROLE = "delete-role";
    public static final String GET_ROLE_DETAILS = "get-role-details";
    public static final String GET_ALL_ROLES = "get-all-roles";

    // Users
    public static final String CREATE_USER = "create-user";
    public static final String UPDATE_USER = "update-user";
    public static final String DELETE_USER = "delete-user";
    public static final String GET_USER_DETAILS = "get-user-details";
    public static final String GET_ALL_USERS = "get-all-users";

    // Books
    public static final String CREATE_BOOK = "create-book";
    public static final String UPDATE_BOOK = "update-book";
    public static final String DELETE_BOOK = "delete-book";
    public static final String GET_BOOK_DETAILS = "get-book-details";
    public static final String GET_ALL_BOOKS = "get-all-books";

    // Patrons
    public static final String CREATE_PATRON = "create-patron";
    public static final String UPDATE_PATRON = "update-patron";
    public static final String DELETE_PATRON = "delete-patron";
    public static final String GET_PATRON_DETAILS = "get-patron-details";
    public static final String GET_ALL_PATRONS = "get-all-patrons";

    // Borrow
    public static final String BORROW_BOOK = "borrow-book";
    public static final String RETURN_BOOK = "return-book";


}
