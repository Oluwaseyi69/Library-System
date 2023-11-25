package org.ebook.services;

import org.ebook.data.model.Librarian;
import org.ebook.data.repository.BookRepository;
import org.ebook.data.repository.BorrowedBookRepository;
import org.ebook.data.repository.LibrarianRepository;
import org.ebook.data.repository.UserRepository;
import org.ebook.dtos.request.*;
import org.ebook.exception.LibrarianAlreadyExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


@SpringBootTest
public class LibrarianServiceImplTest {
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private LibrarianService librarianService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;
    @Autowired
    private UserRepository userRepository;


    @BeforeEach
    public void doThisFirst(){
        librarianRepository.deleteAll();
        bookRepository.deleteAll();
        userRepository.deleteAll();
        borrowedBookRepository.deleteAll();


    }
    @Test
    public void testThat_LibrarianCanRegister(){
        RegisterLibrarianRequest registerLibrarianRequest = new RegisterLibrarianRequest();
        registerLibrarianRequest.setLibrarianName("Seyi");
        registerLibrarianRequest.setPassword("password");

        librarianService.registerLibrarian(registerLibrarianRequest);
        assertThat(librarianRepository.count(), is(1L));

    }
    @Test
    public void testThatLibrarianUniqueName(){
        RegisterLibrarianRequest registerLibrarianRequest = new RegisterLibrarianRequest();
        registerLibrarianRequest.setLibrarianName("Seyi");
        registerLibrarianRequest.setPassword("password");

        librarianService.registerLibrarian(registerLibrarianRequest);
        assertThat(librarianRepository.count(), is(1L));

        RegisterLibrarianRequest registerLibrarianRequest1 = new RegisterLibrarianRequest();
        registerLibrarianRequest1.setLibrarianName("Seyi");
        registerLibrarianRequest1.setPassword("password");
        assertThrows(LibrarianAlreadyExist.class, ()->librarianService.registerLibrarian(registerLibrarianRequest1));
    }

    @Test
    public void testThat_A_RegisteredLibrarianCanLogIn(){
        RegisterLibrarianRequest registerLibrarianRequest = new RegisterLibrarianRequest();
        registerLibrarianRequest.setLibrarianName("Seyi");
        registerLibrarianRequest.setPassword("password");

        librarianService.registerLibrarian(registerLibrarianRequest);
        assertThat(librarianRepository.count(), is(1L));

        LogInLibrarianRequest logInLibrarianRequest = new LogInLibrarianRequest();
        logInLibrarianRequest.setLibrarianName("Seyi");
        logInLibrarianRequest.setPassword("password");

        Librarian librarian = librarianService.login(logInLibrarianRequest);
        assertThat(librarian.isLoggedIn(), is(true));
    }
    @Test
    public void testThatLibrarianCanAddBook(){
        RegisterLibrarianRequest registerLibrarianRequest = new RegisterLibrarianRequest();
        registerLibrarianRequest.setLibrarianName("Seyi");
        registerLibrarianRequest.setPassword("password");

        librarianService.registerLibrarian(registerLibrarianRequest);
        assertThat(librarianRepository.count(), is(1L));

        LogInLibrarianRequest logInLibrarianRequest = new LogInLibrarianRequest();
        logInLibrarianRequest.setLibrarianName("Seyi");
        logInLibrarianRequest.setPassword("password");

        AddBookRequest addBookRequest = new AddBookRequest();
        addBookRequest.setTitle("The Great Gatsby");
        addBookRequest.setAuthor("Seyi");
        librarianService.addBook(addBookRequest);
        assertThat(bookRepository.count(), is(1L));
    }

    @Test
    public void testThatLibrarianCanAddMultipleBooks(){
        RegisterLibrarianRequest registerLibrarianRequest = new RegisterLibrarianRequest();
        registerLibrarianRequest.setLibrarianName("Seyi");
        registerLibrarianRequest.setPassword("password");

        librarianService.registerLibrarian(registerLibrarianRequest);
        assertThat(librarianRepository.count(), is(1L));

        LogInLibrarianRequest logInLibrarianRequest = new LogInLibrarianRequest();
        logInLibrarianRequest.setLibrarianName("Seyi");
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
    }
    @Test
    public void testThatLibrarianCanInputReturnedBook(){
        RegisterLibrarianRequest registerLibrarianRequest = new RegisterLibrarianRequest();
        registerLibrarianRequest.setLibrarianName("Seyi");
        registerLibrarianRequest.setPassword("password");

        librarianService.registerLibrarian(registerLibrarianRequest);
        assertThat(librarianRepository.count(), is(1L));

        LogInLibrarianRequest logInLibrarianRequest = new LogInLibrarianRequest();
        logInLibrarianRequest.setLibrarianName("Seyi");
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

        BorrowBookRequest borrowBookRequest = new BorrowBookRequest();
        borrowBookRequest.setBorrower("Seyi");
        borrowBookRequest.setTitle("Ti japa, tiroko oko yanibo");
        borrowBookRequest.setAuthor("jj rowlings");
        borrowBookRequest.setDueDate(LocalDate.of(2023,11,2));

        librarianService.lendBook(borrowBookRequest);
        assertThat(borrowedBookRepository.count(), is (1L));

        ReturnBookRequest returnBookRequest = new ReturnBookRequest();
        returnBookRequest.setReturner("Seyi");
        returnBookRequest.setBookName("Ti japa, tiroko oko yanibo");
        returnBookRequest.setBookAuthor("jj rowlings");
        librarianService.returnedBorrowedBook(returnBookRequest);
        assertThat(borrowedBookRepository.count(), is(0L));

    }

}