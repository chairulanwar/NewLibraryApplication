package com.irul.newLibrary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;

    @NotBlank(message = "bookTitle must not be null or empty")
    @Size(min = 3, max = 100, message = "bookTitle must be between 3 and 100 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "bookTitle must contain only letters, numbers, and spaces")
    private String bookTitle;

    @NotBlank(message = "bookAuthor must not be null or empty")
    @Size(min = 3, max = 100, message = "bookAuthor must be between 3 and 100 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9 \\-]*$", message = "bookAuthor must contain only letters, numbers, spaces, and hyphens")
    private String bookAuthor;

    @NotBlank(message = "bookPublisher must not be null or empty")
    @Size(min = 3, max = 100, message = "bookPublisher must be between 3 and 100 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9 \\-]*$", message = "bookPublisher must contain only letters, numbers, spaces, and hyphens")
    private String bookPublisher;

    @NotBlank(message = "bookCategory must not be null or empty")
    @Size(min = 3, max = 100, message = "bookCategory must be between 3 and 100 characters long")
    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "bookCategory must contain only letters, numbers, and spaces")
    private String bookCategory;
}