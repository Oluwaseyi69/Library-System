package org.ebook.services;

import org.ebook.data.model.Book;
import org.ebook.data.model.BorrowedBook;
import org.ebook.data.model.User;
import org.ebook.data.repository.BorrowedBookRepository;
import org.ebook.dtos.BorrowBookRequest;
import org.ebook.dtos.ReturnBookRequest;
import org.ebook.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.ebook.utils.Mapper.map;

@Service
public class BorrowedBookServiceImpl implements BorrowedBookService{
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;
    @Override
    public void borrowedBook(BorrowBookRequest borrowBookRequest) {
        User user = userService.findUser(borrowBookRequest.getBorrower());
        Book book = bookService.findBookToBorrow(borrowBookRequest);

        BorrowedBook borrowedBook = map(borrowBookRequest, user, book);
        borrowedBookRepository.save(borrowedBook);
    }

    @Override
    public void returnBook(ReturnBookRequest returnBookRequest) {
        User user = userService.findUser(returnBookRequest.getReturner());
        Book book = bookService.findBookToReturn(returnBookRequest);

        Optional<BorrowedBook>returnedBook = borrowedBookRepository.findBookByBookNameAndBorrower(book, user);
        if (returnedBook.isEmpty())throw  new BookNotFoundException("Book Not Borrowed");
        borrowedBookRepository.delete(returnedBook.get());
    }

    @Override
    public List<BorrowedBook> findBookBorrowedBy(User user) {
        return borrowedBookRepository.findBookByBorrower(user);
    }
}
