package org.example.library.service;

import org.example.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BootService {
    Page<Book> getAllBooks(Pageable pageable);

    Optional<Book> getBookById(Long id);

    Book addBook(Book book);

    Book updateBook(Long id, Book updatedBook);

    void deleteBook(Long id);
}
