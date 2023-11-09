package org.ebook.dtos;

import lombok.Data;

@Data
public class RegisterLibrarianRequest {
    private String librarianName;
    private String password;
}
