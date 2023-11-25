package org.ebook.dtos.response;

import lombok.Data;

@Data
public class AddBookResponse {
    private String book;
    private String dateAdded;
}
