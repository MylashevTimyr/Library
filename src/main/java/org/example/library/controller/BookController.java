package org.example.library.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.library.DTO.BookDto;
import org.example.library.model.Book;
import org.example.library.service.impl.BookServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;
    private final ModelMapper modelMapper;

    @GetMapping
    public Page<BookDto> getAllBooks(Pageable pageable) {
        return bookService.getAllBooks(pageable)
                .map(book -> modelMapper.map(book, BookDto.class));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        return ResponseEntity.ok(modelMapper.map(book, BookDto.class));
    }

    @PostMapping
    public ResponseEntity<BookDto> addBook(@RequestBody Book book) {
        Book savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modelMapper.map(savedBook, BookDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(id, updatedBook);
        return ResponseEntity.ok(modelMapper.map(book, BookDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

