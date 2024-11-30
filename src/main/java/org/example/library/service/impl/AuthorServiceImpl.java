package org.example.library.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.library.model.Author;
import org.example.library.repository.AuthorRepository;
import org.example.library.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public Page<Author> getAllAuthors(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    @Override
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author updatedAuthor) {
        return authorRepository.findById(id).map(existingAuthor -> {
            existingAuthor.setName(updatedAuthor.getName());
            existingAuthor.setBiography(updatedAuthor.getBiography());
            return authorRepository.save(existingAuthor);
        }).orElseThrow(() -> new RuntimeException("Author not found"));
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
