package org.example.library.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDto {
    private Long id;
    private String title;
    private String genre;
    private AuthorDto author;
}
