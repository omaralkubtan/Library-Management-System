## Introduction
The Library Management System is a software built using Spring Boot to handle the primary housekeeping functions of a library. Libraries rely on the system to manage books, patrons, and borrowing records.

## Features
- **User-friendly Interface**: The system has a simple and attractive `Swagger` interface that offers a great user experience.
- **Efficient Book Search**: Users can search for books based on book id, book name, or author name.
- **Book Issue and Return**: The system allows users to issue and return books with ease.
- **Data Management**: The system helps both students and library manager to keep a constant track of all the books available in the library.

## Prerequisites
- JDK 17 or above
- PostgreSQL

## Installation
To set up the Library Management System, follow these steps:

1. Clone the repository from GitHub.
2. Install the necessary dependencies by reloading the `pom.xml` file.
3. Install PostgreSQL and create a database named `LMS`.
4. Set the username of PostgreSQL to `postgres` and the password to `5238738`.
5. Run the server.

## Usage
After successful installation, you can run the application. The system provides an easy-to-use interface for managing the library. You can add new books, issue books to users, and accept returns.

## API Endpoints
The Library Management System provides several API endpoints for interacting with the application. These endpoints allow you to retrieve information about books, users, and transactions.

- Book management endpoints:
  - `GET /api/books`: Retrieve a list of all books.
  - `GET /api/books/{id}`: Retrieve details of a specific book by ID.
  - `POST /api/books`: Add a new book to the library.
  - `PUT /api/books/{id}`: Update an existing book's information.
  - `DELETE /api/books/{id}`: Remove a book from the library.
- Patron management endpoints:
  - `GET /api/patrons`: Retrieve a list of all patrons.
  - `GET /api/patrons/{id}`: Retrieve details of a specific patron by ID.
  - `POST /api/patrons`: Add a new patron to the system.
  - `PUT /api/patrons/{id}`: Update an existing patron's information.
  - `DELETE /api/patrons/{id}`: Remove a patron from the system.
- Borrowing endpoints:
  - `POST /api/borrow/{bookId}/patron/{patronId}`: Allow a patron to borrow a book.
  - `PUT /api/return/{bookId}/patron/{patronId}`: Record the return of a borrowed book by a patron.

## Testing
To test the APIs, you can open the Swagger UI after running the server using this URL: `http://localhost:9000/api/swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config#/`. Alternatively, you can call the APIs from Postman.

## Contributing
Contributions to the Library Management System are welcome! Please read the contributing guide to learn about our development process, how to propose bugfixes and improvements, and how to build and test your changes.
