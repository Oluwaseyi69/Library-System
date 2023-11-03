package org.ebook.services;

import org.ebook.data.model.Book;
import org.ebook.data.model.BorrowedBook;
import org.ebook.data.model.User;
import org.ebook.dtos.*;

import java.util.List;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerUserRequest);

    User login(LogInUserRequest logInUserRequest);
    Book borrowBook(BorrowBookRequest borrowBookRequest);

    List<Book>viewBooks();
    Book searchBook(String bookName, String authorName);
    List<BorrowedBook> viewListOfBorrowedBooks(String username);
    User findUser(String borrower);
}
