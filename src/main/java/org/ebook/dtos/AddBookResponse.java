package org.ebook.dtos;

import lombok.Data;

@Data
public class AddBookResponse {
    private String book;
    private String dateAdded;
}
