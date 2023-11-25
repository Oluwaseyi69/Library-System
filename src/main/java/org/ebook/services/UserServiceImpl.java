package org.ebook.services;

import org.ebook.data.model.Book;
import org.ebook.data.model.BorrowedBook;
import org.ebook.data.model.User;
import org.ebook.data.repository.BookRepository;
import org.ebook.data.repository.UserRepository;
import org.ebook.dtos.request.BorrowBookRequest;
import org.ebook.dtos.request.LogInUserRequest;
import org.ebook.dtos.request.RegisterUserRequest;
import org.ebook.dtos.response.RegisterUserResponse;
import org.ebook.exception.IncorrectCredentialsException;
import org.ebook.exception.UserAlreadyExists;
import org.ebook.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.ebook.utils.Mapper.map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BorrowedBookService borrowedBookService;



    @Override
    public RegisterUserResponse register(RegisterUserRequest registerUserRequest) {
        findUser(registerUserRequest);
        return map(userRepository.save(map(registerUserRequest)));
    }


    @Override
    public User login(LogInUserRequest logInUserRequest) {
        Optional<User> user = retrieveUser(logInUserRequest.getUsername());
        if(user.isEmpty()) throw new UserNotFoundException("User not found");
        if(!user.get().getPassword().equals(logInUserRequest.getPassword()))
            throw new IncorrectCredentialsException("Incorrect Username or Password");

        user.get().setLoggedIn(true);
        userRepository.save(user.get());
        return user.get();
    }

    @Override
    public Book borrowBook(BorrowBookRequest borrowBookRequest) {
        return bookService.borrow(borrowBookRequest);
    }

    @Override
    public List<Book> viewBooks() {
      return bookService.retrieveAllBooks();
    }

    @Override
    public Book searchBook(String bookName, String authorName) {
        Book book = bookService.findBook(bookName, authorName);
        return book;
    }

    @Override
    public List<BorrowedBook> viewListOfBorrowedBooks(String username) {
        return borrowedBookService.findBookBorrowedBy(findUser(username));
    }

    @Override
    public User findUser(String borrower) {
        Optional<User> user = getUser(borrower);
        if(user.isEmpty())throw new UserNotFoundException("User not found");
        return user.get();
    }
    private Optional<User> getUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    private Optional<User> retrieveUser(String username){
        Optional<User> user = userRepository.findByUsername(username);
        return user;
    }

    private void findUser(RegisterUserRequest registerUserRequest){
        Optional<User> user = userRepository.findByUsername(registerUserRequest.getUsername());
        if (user.isPresent())
                throw new UserAlreadyExists("User already Exist");
    }


}


