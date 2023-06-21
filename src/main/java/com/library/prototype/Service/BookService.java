package com.library.prototype.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.prototype.Entity.Books;
import com.library.prototype.Entity.GlobalResponse;
import com.library.prototype.Repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {


    private final BookRepository bookRepository;


    public ResponseEntity<?> getAllBooks(){
        try{
            List<Books> books = bookRepository.getAllValidBooks();
            if(books.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else{
                return ResponseEntity.ok().body(books);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> saveBook(Books books){
        try{
            if(books == null || (books.getBookId() == null || books.getBookName() == null
                || books.getBookCategoryId() == null || books.getBookCount() == null
                || books.getBookDescription() == null )){
                    return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.NO_CONTENT);
                }
            else{
                bookRepository.save(books);
                var res = GlobalResponse.builder().responseData(books).httpStatus(HttpStatus.OK).build();
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> updateBook(Books books){
        try{
            if(books == null || (books.getBookId() == null || books.getBookName() == null 
                || books.getBookCategoryId() == null || books.getBookCount() == null
                || books.getBookDescription() == null )){
                    return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.NO_CONTENT);
                }
            else{
                bookRepository.updateBook(books.getBookId(), books.getBookName(), books.getBookDescription(),
                     books.getBookCount(), books.getBookCategoryId(), books.getBookPicture());
                var res = GlobalResponse.builder().responseData(books).httpStatus(HttpStatus.OK).build();
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> softDeleteBook(String bookId){
        try{
            if(bookId == null  ){
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.NO_CONTENT);
            }
            else{
                bookRepository.softDeleteBook(bookId);
                return new ResponseEntity<>("BOOK DELETED SUCCESSFULLY", HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
}
