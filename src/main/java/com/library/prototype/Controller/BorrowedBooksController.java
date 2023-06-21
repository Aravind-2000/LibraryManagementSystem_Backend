package com.library.prototype.Controller;


import com.library.prototype.Entity.BooksBorrowed;
import com.library.prototype.Service.BooksBorrowedService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/borrowedBooks")
@AllArgsConstructor
public class BorrowedBooksController {

    private final BooksBorrowedService booksBorrowedService;

    @PostMapping("/save")
    public ResponseEntity<?> byUser(HttpServletRequest rq,  @RequestBody BooksBorrowed booksBorrowed){
        return booksBorrowedService.save(rq,booksBorrowed);
    }

    @GetMapping("/search/byUser/{userEmail}")
    public ResponseEntity<?> byUser(@PathVariable String userEmail){
        return booksBorrowedService.getByUser(userEmail);
    }


    @GetMapping("/search/byUser/{userEmail}/byBookName/{bookName}")
    public ResponseEntity<?> byUserAndBookName(@PathVariable String userEmail, @PathVariable String bookName){
        return booksBorrowedService.searchByBookNameAndUser(bookName, userEmail);
    }


    @GetMapping("/search/byUser/{userEmail}/byBookId/{bookId}")
    public ResponseEntity<?> byUserAndBookId(@PathVariable String userEmail, @PathVariable String bookId){
        return booksBorrowedService.searchByBookIdAndUser(bookId, userEmail);
    }


}
