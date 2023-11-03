package org.ebook.data.model;

import lombok.Data;

@Data
public class Librarian {
    private String id;
    private String librarianName;
    private String password;
    private boolean isLoggedIn;
}
