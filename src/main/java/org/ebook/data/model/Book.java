package org.ebook.data.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@Data
@Document("Book")
public class Book {
    private String id;
    private String title;
    private String isbn;
    private String author;
    private int booksAvailable;
    private LocalDate datedAdded = LocalDate.now();
    private LocalDate timeAdded = LocalDate.now();
    private Long price;



}
