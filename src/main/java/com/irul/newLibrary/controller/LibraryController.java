package com.irul.newLibrary.controller;

import com.irul.newLibrary.entity.Book;
import com.irul.newLibrary.service.LibraryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    private final Logger LOGGER = LoggerFactory.getLogger(LibraryController.class);

    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {
        Book createdBook = libraryService.addBook(book);
        LOGGER.info("Book added: " + createdBook);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        LOGGER.info("Getting all books");
        return libraryService.getAllBooks();
    }

    @GetMapping("/book/{bookId}")
    public Book getBookById(@PathVariable Long bookId) throws Exception {
        LOGGER.info("Getting book with ID: " + bookId);
        return libraryService.getBookById(bookId);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String bookTitle,
            @RequestParam(required = false) String bookAuthor,
            @RequestParam(required = false) String bookCategory) {
        LOGGER.info("Searching books with title: " + bookTitle + ", author: " + bookAuthor + ", category: " + bookCategory);
        return libraryService.searchBooks(bookTitle, bookAuthor, bookCategory);
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<Map<String, Object>> updateBook(@PathVariable Long bookId, @RequestBody Book book) {
        LOGGER.info("Updating book with ID: " + bookId);
        return libraryService.updateBook(bookId, book);
    }

    @DeleteMapping("/book/{bookId}")
    public ResponseEntity<Map<String, String>> deleteBookById(@PathVariable Long bookId) {
        LOGGER.info("Deleting book with ID: " + bookId);
        return libraryService.deleteBookById(bookId);
    }

}