package com.irul.newLibrary.repository;

import com.irul.newLibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibraryRepository extends JpaRepository<Book, Long> {

    boolean existsByBookTitle(String bookTitle);

    List<Book> findByBookTitleOrBookAuthorOrBookCategory(String bookTitle, String bookAuthor, String bookCategory);
}
