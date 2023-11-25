package org.ebook.services;

import org.ebook.data.model.Book;
import org.ebook.data.repository.BookRepository;
import org.ebook.dtos.request.AddBookRequest;
import org.ebook.dtos.request.BorrowBookRequest;
import org.ebook.dtos.request.RemoveBookRequest;
import org.ebook.dtos.request.ReturnBookRequest;
import org.ebook.exception.BookNotAvailableException;
import org.ebook.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.ebook.utils.Mapper.map;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

//    @Override
//    public Book findBook(FindBookRequest findBookRequest) {
//        Optional<Book>foundBook = bookRepository.
//                findBookByTitleAndAuthor(findBookRequest.getTitle(), findBookRequest.getAuthor());
//
//        if (foundBook.isPresent()) return foundBook.get();
//        Optional<Book>repeatedBook= recheckRepository(findBookRequest);
//        return repeatedBook.orElse(null);
//    }
//    private Optional<Book> recheckRepository(FindBookRequest findBookRequest){
//        return bookRepository.findBookByTitleAndAuthor(
//                findBookRequest.getTitle(), findBookRequest.getAuthor());
//    }

    @Override
    public Book findBook(String bookName, String authorName) {
        Optional<Book> book= getBook(bookName, authorName);
        if(book.isEmpty()) throw new BookNotFoundException("Book  not found");
        return null;
    }

    private Optional <Book> getBook(String bookName,  String authorName){
        return bookRepository.findBookByTitleAndAuthor(bookName, authorName);

    }

    @Override
    public void add(AddBookRequest addBookRequest) {
        Book book = map(addBookRequest);
        bookRepository.save(book);
    }

    @Override
    public Book borrow(BorrowBookRequest borrowBookRequest) {
        Optional<Book> book = bookRepository.findBookByTitleAndAuthor(borrowBookRequest.getTitle(),
                borrowBookRequest.getAuthor());
        if(book.isEmpty()) throw new BookNotAvailableException("Book Not Available");
        book.get().setBooksAvailable(book.get().getBooksAvailable() - 1);
        bookRepository.delete(book.get());
        Book newBook = book.get();
        bookRepository.save(newBook);
        return newBook;

    }

    @Override
    public List<Book> retrieveAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book findBookToReturn(ReturnBookRequest returnBookRequest) {
        Optional<Book> book = getBook(returnBookRequest.getBookName(), returnBookRequest.getBookAuthor());
        if(book.isEmpty()) throw new BookNotFoundException("Book not found");
        return addReturnedBook(book.get());
    }
    private Book addReturnedBook(Book book){
        book.setBooksAvailable(book.getBooksAvailable() + 1);
        bookRepository.save(book);
        return book;
    }

    @Override
    public Book findBookToBorrow(BorrowBookRequest borrowBookRequest) {
        Optional<Book> book = getBook(borrowBookRequest.getTitle(),borrowBookRequest.getAuthor());
        if(book.isEmpty()) throw new BookNotFoundException("Book not found");
        return reducedBookBorrowed(book.get());
    }
    private Book reducedBookBorrowed(Book book){
        book.setBooksAvailable(book.getBooksAvailable()-1);
        bookRepository.save(book);
        return book;
    }

    @Override
    public void remove(RemoveBookRequest removeBookRequest) {
        Optional<Book> book = getBook(removeBookRequest.getBookName(), removeBookRequest.getBookAuthor());
        if(book.isPresent()) bookRepository.delete(book.get());
        else throw new BookNotFoundException("Book not found");
    }
}
