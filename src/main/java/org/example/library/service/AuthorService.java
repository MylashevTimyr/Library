package org.example.library.service;

import org.example.library.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AuthorService {
    Page<Author> getAllAuthors(Pageable pageable);

    Optional<Author> getAuthorById(Long id);

    Author addAuthor(Author author);

    Author updateAuthor(Long id, Author updatedAuthor);

    void deleteAuthor(Long id);
}
