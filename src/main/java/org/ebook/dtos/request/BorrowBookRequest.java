package org.ebook.dtos.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowBookRequest {
    private String title;
    private String author;
    private String borrower;
    private LocalDate dueDate;
}
