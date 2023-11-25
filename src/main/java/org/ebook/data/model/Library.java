package org.ebook.data.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
public class Library {
    private String book;
    private String user;
    @DBRef
    private List<Book>books = new ArrayList<>();

}
