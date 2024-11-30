package org.example.library.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.library.model.Author;
import org.example.library.model.Book;
import org.example.library.repository.AuthorRepository;
import org.example.library.repository.BookRepository;
import org.example.library.service.BootService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BootService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return Optional.ofNullable(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book with ID " + id + " not found")));
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setGenre(updatedBook.getGenre());
            Author author = authorRepository.findById(updatedBook.getAuthor().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Author with ID " + updatedBook.getAuthor().getId() + " not found"));
            existingBook.setAuthor(author);
            return bookRepository.save(existingBook);
        }).orElseThrow(() -> new EntityNotFoundException("Book with ID " + id + " not found"));
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
