package org.ebook.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddBookRequest {
    private String title;
    private String author;
    private String isbn;
    private int booksAvailable;

}
