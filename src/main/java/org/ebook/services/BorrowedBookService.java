package org.ebook.services;

import org.ebook.data.model.BorrowedBook;
import org.ebook.data.model.User;
import org.ebook.dtos.BorrowBookRequest;
import org.ebook.dtos.ReturnBookRequest;

import java.util.List;

public interface BorrowedBookService {
    void borrowedBook(BorrowBookRequest borrowBookRequest);
    void returnBook(ReturnBookRequest returnBookRequest );

    List<BorrowedBook>findBookBorrowedBy(User user);
}
