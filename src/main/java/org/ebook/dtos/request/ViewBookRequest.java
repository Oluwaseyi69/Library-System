package org.ebook.dtos.request;

import lombok.Data;
import org.ebook.data.model.Book;

import java.util.List;

@Data
public class ViewBookRequest {
    private String bookName;
    private String authorName;
}
