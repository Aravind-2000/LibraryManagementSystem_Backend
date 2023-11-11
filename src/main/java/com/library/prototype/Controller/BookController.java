package com.library.prototype.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import com.library.prototype.Entity.Books;
import com.library.prototype.Service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/getallbooks")
    public ResponseEntity<?> getAll() {
        return bookService.getAllBooks();
    }

    @PostMapping("/savebook")
    public ResponseEntity<?> saveBook(@RequestBody Books books) {
        return bookService.saveBook(books);
    }

    @PutMapping("/updatebook")
    public ResponseEntity<?> updateBook(@RequestBody Books book) {
        return bookService.updateBook(book);
    }

    @DeleteMapping("/softdeletebook/{id}")
    public ResponseEntity<?> softDelete(@PathVariable String id) {
        return bookService.softDeleteBook(id);
    }

}
