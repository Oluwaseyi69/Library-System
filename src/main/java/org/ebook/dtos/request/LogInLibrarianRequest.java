package org.ebook.dtos;

import lombok.Data;

@Data
public class LogInLibrarianRequest {
    private String librarianName;
    private String password;
}
