package org.ebook.services;

import org.ebook.data.model.Book;
import org.ebook.data.model.Librarian;
import org.ebook.dtos.*;

public interface LibrarianService {

    boolean isAvailable(FindBookRequest findBookRequest);
    void addBook(AddBookRequest addBookRequest);

    RegisterLibrarianResponse registerLibrarian(RegisterLibrarianRequest registerLibrarianRequest);

    Librarian login(LogInLibrarianRequest logInLibrarianRequest);
    void lendBook(BorrowBookRequest borrowBookRequest);

    void returnedBorrowedBook(ReturnBookRequest returnBookRequest);

    void remove(RemoveBookRequest removeBookRequest);

}
