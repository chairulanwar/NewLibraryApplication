package com.irul.newLibrary.service;

import com.irul.newLibrary.entity.Book;
import com.irul.newLibrary.exception.InvalidBookException;
import com.irul.newLibrary.exception.ResourceNotFoundException;
import com.irul.newLibrary.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Override
    public Book addBook(Book book) {
        if (libraryRepository.existsByBookTitle(book.getBookTitle())) {
            throw new InvalidBookException("A book with the same title already exists");
        }
        return libraryRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return libraryRepository.findAll();
    }

    @Override
    public Book getBookById(Long bookId) {
        return libraryRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
    }

    @Override
    public List<Book> searchBooks(String bookTitle, String bookAuthor, String bookCategory) {
        List<Book> books = libraryRepository.findByBookTitleOrBookAuthorOrBookCategory(bookTitle, bookAuthor, bookCategory);
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("No books found with the given criteria");
        }
        return books;
    }

    @Override
    public ResponseEntity<Map<String, Object>> updateBook(Long bookId, Book book) {
        if (book.getBookTitle() == null || book.getBookTitle().isEmpty()) {
            throw new InvalidBookException("bookTitle must not be null or empty");
        }
        if (book.getBookAuthor() == null || book.getBookAuthor().isEmpty()) {
            throw new InvalidBookException("bookAuthor must not be null or empty");
        }
        if (book.getBookPublisher() == null || book.getBookPublisher().isEmpty()) {
            throw new InvalidBookException("bookPublisher must not be null or empty");
        }
        if (book.getBookCategory() == null || book.getBookCategory().isEmpty()) {
            throw new InvalidBookException("bookCategory must not be null or empty");
        }

        Book existingBook = libraryRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        existingBook.setBookTitle(book.getBookTitle());
        existingBook.setBookAuthor(book.getBookAuthor());
        existingBook.setBookPublisher(book.getBookPublisher());
        existingBook.setBookCategory(book.getBookCategory());

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Book successfully updated");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> deleteBookById(Long bookId) {
        libraryRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        libraryRepository.deleteById(bookId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Book successfully deleted");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
