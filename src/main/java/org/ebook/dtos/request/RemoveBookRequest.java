package org.ebook.dtos.request;

import lombok.Data;

@Data
public class RemoveBookRequest {
    private String bookName;
    private String bookAuthor;
}
