package org.ebook.services;

import org.ebook.data.model.Book;
import org.ebook.dtos.request.AddBookRequest;
import org.ebook.dtos.request.BorrowBookRequest;
import org.ebook.dtos.request.RemoveBookRequest;
import org.ebook.dtos.request.ReturnBookRequest;

import java.util.List;

public interface BookService {

    Book findBook (String bookName, String authorName);

//    Book findBook(FindBookRequest findBookRequest);

    void add(AddBookRequest addBookRequest);
    Book borrow(BorrowBookRequest borrowBookRequest);

    List<Book> retrieveAllBooks();
    Book findBookToReturn(ReturnBookRequest returnBookRequest);
    Book findBookToBorrow(BorrowBookRequest borrowBookRequest);
    void remove (RemoveBookRequest removeBookRequest);
}
