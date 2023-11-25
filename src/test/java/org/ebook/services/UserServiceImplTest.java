package org.ebook.services;

import org.ebook.data.model.Book;
import org.ebook.data.model.User;
import org.ebook.data.repository.BookRepository;
import org.ebook.data.repository.LibrarianRepository;
import org.ebook.data.repository.UserRepository;
import org.ebook.dtos.request.*;
import org.ebook.exception.IncorrectCredentialsException;
import org.ebook.exception.UserAlreadyExists;
import org.ebook.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void doThisFirst(){
        userRepository.deleteAll();
        librarianRepository.deleteAll();
        bookRepository.deleteAll();
    }

    @Test
    public void testToRegister_A_User(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Oluwaseyi");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));
    }
    @Test
    public void testToRegisterWith_A_UniqueName(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Oluwaseyi");
        registerUserRequest.setPassword("password");

        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        RegisterUserRequest registerUserRequest1 = new RegisterUserRequest();
        registerUserRequest1.setUsername("Oluwaseyi");
        registerUserRequest1.setPassword("password");
        assertThrows(UserAlreadyExists.class,()->userService.register(registerUserRequest1));

    }

    @Test
    public void testThatUserCanLogIn(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Seyi");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        LogInUserRequest logInUserRequest = new LogInUserRequest();
        logInUserRequest.setUsername("Seyi");
        logInUserRequest.setPassword("password");
        User user = userService.login(logInUserRequest);
        assertThat(user.isLoggedIn(), is(true));

    }
    @Test
    public void testThatNonRegisterUserCannotLogIn(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Seyi");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        LogInUserRequest logInUserRequest = new LogInUserRequest();
        logInUserRequest.setUsername("Anu");
        logInUserRequest.setPassword("password");
        assertThrows(UserNotFoundException.class,()->userService.login(logInUserRequest));

    }
    @Test
    public void testThatUserCannotLogInWithInCorrectPassword(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Seyi");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        LogInUserRequest logInUserRequest = new LogInUserRequest();
        logInUserRequest.setUsername("Seyi");
        logInUserRequest.setPassword("pasword");
        assertThrows(IncorrectCredentialsException.class,()->userService.login(logInUserRequest));
    }

    @Test
    public void testThatUserCanBorrowBook(){
        RegisterLibrarianRequest registerLibrarianRequest = new RegisterLibrarianRequest();
        registerLibrarianRequest.setLibrarianName("Tomide");
        registerLibrarianRequest.setPassword("password");

        librarianService.registerLibrarian(registerLibrarianRequest);
        assertThat(librarianRepository.count(), is(1L));

        LogInLibrarianRequest logInLibrarianRequest = new LogInLibrarianRequest();
        logInLibrarianRequest.setLibrarianName("Tomide");
        logInLibrarianRequest.setPassword("password");

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("The Great Gatsby");
        addBookRequest.setAuthor("Seyi");
        librarianService.addBook(addBookRequest);
        assertThat(bookRepository.count(), is(1L));

        AddBookRequest addBookRequest1 = new AddBookRequest();
        addBookRequest1.setTitle("Ti japa, tiroko oko yanibo");
        addBookRequest1.setAuthor("jj rowlings");
        addBookRequest1.setBooksAvailable(5);
        librarianService.addBook(addBookRequest1);
        assertThat(bookRepository.count(), is(2L));

        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("Seyi");
        registerUserRequest.setPassword("password");
        userService.register(registerUserRequest);
        assertThat(userRepository.count(), is(1L));

        LogInUserRequest logInUserRequest = new LogInUserRequest();
        logInUserRequest.setUsername("Seyi");
        logInUserRequest.setPassword("password");
        User user = userService.login(logInUserRequest);
        assertThat(user.isLoggedIn(), is(true));

        BorrowBookRequest borrowBookRequest = new BorrowBookRequest();
        borrowBookRequest.setTitle("Ti japa, tiroko oko yanibo");
        borrowBookRequest.setAuthor("jj rowlings");
        Book book = userService.borrowBook(borrowBookRequest);
        assertThat(userRepository.count(), is(1L));
        assertThat(book.getBooksAvailable(), is(4));

    }


}