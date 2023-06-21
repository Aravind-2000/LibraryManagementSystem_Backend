package com.library.prototype.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.prototype.Entity.BookCategory;
import com.library.prototype.Entity.GlobalResponse;
import com.library.prototype.Repository.BookCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookCategoryService {

    private final BookCategoryRepository categoryRepository;


    public ResponseEntity<?> getAllValidCategory(){
        try{
            List<BookCategory> categories = categoryRepository.getAllValidBookCategories();
            if(categories.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else{
                return ResponseEntity.ok().body(categories);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    public ResponseEntity<?> saveCategory(BookCategory category){
        try{
            if(category == null || (category.getCategoryName() == null || category.getCategoryDescription() == null)){
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            }
            else{
                categoryRepository.save(category);
                var res  = GlobalResponse.builder().responseData(category).httpStatus(HttpStatus.OK).build();
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> updateCategory(BookCategory category){
        try{
            if(category == null || (category.getRandomAutoIncrementedId() == null || category.getCategoryName() == null || category.getCategoryDescription() == null)){
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            }
            else{
                categoryRepository.updateBookCategory(category.getCategoryName(), category.getCategoryDescription(), category.getRandomAutoIncrementedId());
                var res  = GlobalResponse.builder().responseData(category).httpStatus(HttpStatus.OK).build();
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> softDeleteCategory(Long catId){
        try{
            if(catId == null){
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            }
            else{
                categoryRepository.softDeleteBookCategory(catId);
                return new ResponseEntity<>("BOOKCATEGORY DELETED SUCCESSFULLY", HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





    
}
