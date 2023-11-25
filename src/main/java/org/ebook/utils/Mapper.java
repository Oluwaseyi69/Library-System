package org.ebook.utils;

import org.ebook.data.model.Book;
import org.ebook.data.model.BorrowedBook;
import org.ebook.data.model.User;
import org.ebook.dtos.request.AddBookRequest;
import org.ebook.dtos.request.BorrowBookRequest;
import org.ebook.dtos.request.RegisterUserRequest;
import org.ebook.dtos.response.RegisterUserResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mapper {
    public static User map(RegisterUserRequest registerUserRequest){
        User user = new User();
        user.setUsername(registerUserRequest.getUsername());
        user.setPassword(registerUserRequest.getPassword());

        return user;
    }

    public static RegisterUserResponse map(User user){
        RegisterUserResponse registerUserResponse = new RegisterUserResponse();
        registerUserResponse.setUsername(user.getUsername());
        registerUserResponse.setRegisterDate(DateTimeFormatter
                .ofPattern("EEE dd/MMM/yyyy HH:mm:ss a")
                .format(LocalDateTime.now()));
        return registerUserResponse;
    }

    public static Book map(AddBookRequest addBookRequest) {
        Book book = new Book();
        book.setTitle(addBookRequest.getTitle());
        book.setAuthor(addBookRequest.getAuthor());
        book.setIsbn(addBookRequest.getIsbn());
        book.setBooksAvailable(addBookRequest.getBooksAvailable());
        return book;
    }

    public static BorrowedBook map (BorrowBookRequest borrowBookRequest, User user, Book book){
        BorrowedBook borrowedBook = new BorrowedBook();
        borrowedBook.setBorrower(user);
        borrowedBook.setBookName(book);
        borrowedBook.setBorrowDate(LocalDate.now());
        borrowedBook.setDueDate(borrowBookRequest.getDueDate());
        return borrowedBook;
    }


}
