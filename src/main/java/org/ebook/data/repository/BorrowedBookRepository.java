package org.ebook.data.repository;

import org.ebook.data.model.Book;
import org.ebook.data.model.BorrowedBook;
import org.ebook.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowedBookRepository extends MongoRepository<BorrowedBook, String > {
    Optional<BorrowedBook> findBookByBookNameAndBorrower(Book book, User user);

    List<BorrowedBook>findBookByBorrower(User user);
}
