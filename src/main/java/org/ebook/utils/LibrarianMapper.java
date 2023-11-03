package org.ebook.utils;

import org.ebook.data.model.Book;
import org.ebook.data.model.Librarian;
import org.ebook.dtos.AddBookRequest;
import org.ebook.dtos.FindBookRequest;
import org.ebook.dtos.RegisterLibrarianRequest;
import org.ebook.dtos.RegisterLibrarianResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LibrarianMapper {
    public static Librarian map(RegisterLibrarianRequest registerLibrarianRequest){
        Librarian librarian = new Librarian();
        librarian.setLibrarianName(registerLibrarianRequest.getLibrarianName());
        librarian.setPassword(registerLibrarianRequest.getPassword());

        return librarian;
    }

    public static RegisterLibrarianResponse map(Librarian librarian){
        RegisterLibrarianResponse registerLibrarianResponse = new RegisterLibrarianResponse();
        registerLibrarianResponse.setLibrarianName(librarian.getLibrarianName());
        registerLibrarianResponse.setDateCreated(DateTimeFormatter
                .ofPattern("EEE dd/MMM/yyyy HH:mm:ss a")
                .format(LocalDateTime.now()));
        return registerLibrarianResponse;
    }

    public static Book map(FindBookRequest findBookRequest){
        Book book = new Book();
        book.setAuthor(findBookRequest.getAuthor());
        book.setTitle(findBookRequest.getTitle());

        return book;
    }

}
