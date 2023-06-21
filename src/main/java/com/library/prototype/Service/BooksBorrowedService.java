package com.library.prototype.Service;


import com.library.prototype.Entity.BooksBorrowed;
import com.library.prototype.Entity.GlobalResponse;
import com.library.prototype.Repository.BorrowedBooksRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BooksBorrowedService {

    private final BorrowedBooksRepository borrowedBooksRepository;


    public ResponseEntity<?> save(HttpServletRequest request, BooksBorrowed booksBorrowed){
        try{
            if(booksBorrowed == null || booksBorrowed.getBookId() == null ){
                return new ResponseEntity<>("DATA IS MISSING", HttpStatus.NO_CONTENT);
            }
            else{
                booksBorrowed.setUser(request.getUserPrincipal().getName());
                borrowedBooksRepository.save(booksBorrowed);
                var res = GlobalResponse.builder().responseData(booksBorrowed).httpStatus(HttpStatus.OK).build();
                return ResponseEntity.ok().body(res);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> getByUser(String userEmail){
        try{
            if(userEmail == null){
                return new ResponseEntity<>("DATA IS MISSING", HttpStatus.NO_CONTENT);
            }
            else{
                List<BooksBorrowed> booksBorrowedList = borrowedBooksRepository.getBooksBorrowedByUser(userEmail);
                return ResponseEntity.ok().body(booksBorrowedList);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> searchByBookNameAndUser(String bookName, String userEmail){
        try{
            if(bookName == null || userEmail == null){
                return new ResponseEntity<>("DATA IS MISSING", HttpStatus.NO_CONTENT);
            }
            else{
                List<BooksBorrowed> booksBorrowedList = borrowedBooksRepository.getBooksBorrowedByBookName(bookName, userEmail);
                return ResponseEntity.ok().body(booksBorrowedList);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> searchByBookIdAndUser(String bookId, String userEmail){
        try{
            if(userEmail == null || bookId == null){
                return new ResponseEntity<>("DATA IS MISSING", HttpStatus.NO_CONTENT);
            }
            else{
                List<BooksBorrowed> booksBorrowedList = borrowedBooksRepository.getBooksBorrowedByBookId(bookId, userEmail);
                return ResponseEntity.ok().body(booksBorrowedList);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
