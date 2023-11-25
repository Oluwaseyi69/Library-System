package org.ebook.services;

import org.ebook.data.model.Librarian;
import org.ebook.data.repository.LibrarianRepository;
import org.ebook.dtos.request.*;
import org.ebook.dtos.response.RegisterLibrarianResponse;
import org.ebook.exception.IncorrectCredentialsException;
import org.ebook.exception.LibrarianAlreadyExist;
import org.ebook.exception.LibrarianNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.ebook.utils.LibrarianMapper.map;

@Service
public class LibrarianServiceImpl implements LibrarianService{
    @Autowired
    private BookService bookService;

    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private BorrowedBookService borrowedBookService;

    @Override
    public boolean isAvailable(FindBookRequest findBookRequest) {
        return true;
    }

    @Override
    public void addBook(AddBookRequest addBookRequest) {
        bookService.add(addBookRequest);

    }



    @Override
    public RegisterLibrarianResponse registerLibrarian(RegisterLibrarianRequest registerLibrarianRequest) {
        findLibrarian(registerLibrarianRequest);
        return map(librarianRepository.save(map(registerLibrarianRequest)));
    }

    @Override
    public Librarian login(LogInLibrarianRequest logInLibrarianRequest) {
        Optional<Librarian> librarian = retrieveLibrarian(logInLibrarianRequest.getLibrarianName());
        if(librarian.isEmpty()) throw new LibrarianNotFoundException("Librarian Not Found");
        if(!librarian.get().getPassword().equals(logInLibrarianRequest.getPassword()))
            throw new IncorrectCredentialsException("Incorrect Username or Password");

        librarian.get().setLoggedIn(true);
        librarianRepository.save(librarian.get());
        return librarian.get();
    }

    @Override
    public void lendBook(BorrowBookRequest borrowBookRequest) {
        borrowedBookService.borrowedBook(borrowBookRequest);
    }

    @Override
    public void returnedBorrowedBook(ReturnBookRequest returnBookRequest) {
        borrowedBookService.returnBook(returnBookRequest);
    }

    @Override
    public void remove(RemoveBookRequest removeBookRequest) {
        bookService.remove(removeBookRequest);
    }

    private void findLibrarian(RegisterLibrarianRequest registerLibrarianRequest) {
        Optional<Librarian>librarian = librarianRepository.findByLibrarianName(registerLibrarianRequest.getLibrarianName());
        if(librarian.isPresent()){
            throw new LibrarianAlreadyExist("Librarian Already Exist");
        }
    }

    private Optional<Librarian> retrieveLibrarian(String username){
        Optional<Librarian> librarian = librarianRepository.findByLibrarianName(username);
        return librarian;
    }


}
