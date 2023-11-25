package org.ebook.controller;

import org.ebook.dtos.request.*;
import org.ebook.services.LibrarianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Librarian")
public class LibrarianController {
    @Autowired
    private LibrarianService librarianService;

    @PostMapping("/registerLibrarian")
    public String register(@RequestBody RegisterLibrarianRequest registerLibrarianRequest){
        try{
            librarianService.registerLibrarian(registerLibrarianRequest);
            return "Librarian Successfully Registered";
        }catch (Exception message){
            return message.getMessage();
        }
    }

    @PostMapping("/Login")
    public String login(@RequestBody LogInLibrarianRequest logInLibrarianRequest){
        try{
            librarianService.login(logInLibrarianRequest);
            return "LogIn Successfully";
        }catch (Exception message){
            return message.getMessage();
        }
    }

    @PostMapping("/AddBook")
    public String addBook(@RequestBody AddBookRequest addBookRequest){
        try{
            librarianService.addBook(addBookRequest);
            return "Book Added";
        }catch (Exception error){
            return error.getMessage();
        }
    }

    @DeleteMapping("/removeBook")
    public String removeBook(@RequestBody RemoveBookRequest removeBookRequest){
        try {
            librarianService.remove(removeBookRequest);
            return "Book Removed";
        }catch (Exception error){
            return error.getMessage();
        }
    }

    @PostMapping("/lendBook")
    public String BorrowBook(@RequestBody BorrowBookRequest borrowBookRequest){
        try{
            librarianService.lendBook(borrowBookRequest);
            return "Successful";
        }catch (Exception error){
            return error.getMessage();
        }
    }
    @PostMapping("/returnBook")
    public String returnedBook(@RequestBody ReturnBookRequest returnBookRequest){
        try {
            librarianService.returnedBorrowedBook(returnBookRequest);
            return "Successful";
        }catch (Exception error){
            return error.getMessage();
        }
    }


}
