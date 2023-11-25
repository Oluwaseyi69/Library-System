package org.ebook.dtos.request;

import lombok.Data;

@Data
public class RegisterLibrarianRequest {
    private String librarianName;
    private String password;
}
