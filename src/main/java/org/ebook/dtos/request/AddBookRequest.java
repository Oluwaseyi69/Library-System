package org.ebook.dtos.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AddBookRequest {
    private String title;
    private String author;
    private String isbn;
    private int booksAvailable;

}
