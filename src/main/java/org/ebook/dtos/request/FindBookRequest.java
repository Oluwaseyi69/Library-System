package org.ebook.dtos.request;


import lombok.Data;
import org.ebook.data.model.Book;

import java.util.List;

@Data
public class FindBookRequest {
    private String title;
    private String author;

    private List<Book>shelf;
}
