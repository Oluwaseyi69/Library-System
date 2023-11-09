package org.ebook.dtos;

import lombok.Data;

@Data
public class ReturnBookRequest {
    private String bookName;
    private String bookAuthor;
    private String returner;
}
