package com.irul.newLibrary.service;

import com.irul.newLibrary.entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface LibraryService {

    public Book addBook(Book book);

    public List<Book> getAllBooks();

    public Book getBookById(Long bookId) throws Exception;

    public List<Book> searchBooks(String bookTitle, String bookAuthor, String bookCategory);

    public ResponseEntity<Map<String, Object>> updateBook(Long bookId, Book book);

    public ResponseEntity<Map<String, String>> deleteBookById(Long bookId);
}
