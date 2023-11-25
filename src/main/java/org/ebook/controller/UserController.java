package org.ebook.controller;

import lombok.AllArgsConstructor;
import org.ebook.data.model.Book;
import org.ebook.dtos.request.BorrowBookRequest;
import org.ebook.dtos.request.LogInUserRequest;
import org.ebook.dtos.request.RegisterUserRequest;
import org.ebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5501")
@RequestMapping("/elibrary")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping("/registerUser")
    public String register(@RequestBody RegisterUserRequest registerUserRequest){
        try{
            userService.register(registerUserRequest);
            return "Successfully Registered";
        }catch (Exception error){
            return error.getMessage();
        }
    }

    @PostMapping("/LoginUser")
    public String login (@RequestBody LogInUserRequest logInUserRequest){
        try{
            userService.login(logInUserRequest);
            return "Log in Successful";
        }catch (Exception error){
            return error.getMessage();
        }
    }

    @PostMapping("/borrowBook")
    public String borrow(@RequestBody BorrowBookRequest borrowBookRequest){
        try{
            userService.borrowBook(borrowBookRequest);
            return "Borrow Complete";
        }catch (Exception error){
            return error.getMessage();
        }
    }
    @GetMapping("/viewBook")
    public List<Book> viewBook(){
        List<Book> viewBooks = userService.viewBooks();
        return viewBooks;
    }
    @GetMapping("/searchBook/{title}/{author}")
    public Object searchBook(@PathVariable String title, @PathVariable String author){
        try{
            return userService.searchBook(title, author);
        }catch (Exception error){
            return error.getMessage();
        }
    }

    @GetMapping("ListOfBorrowedBooks/ {username}")
    public Object borrowedBooks(@PathVariable String username){
        try {
            return userService.viewListOfBorrowedBooks(username);
        }catch (Exception error){
            return error.getMessage();
        }
    }

}
