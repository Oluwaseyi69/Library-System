package org.ebook.dtos.request;

import lombok.Data;

@Data
public class LogInLibrarianRequest {
    private String librarianName;
    private String password;
}
