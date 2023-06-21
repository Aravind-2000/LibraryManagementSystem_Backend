package com.library.prototype.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.prototype.Entity.BookCategory;
import com.library.prototype.Service.BookCategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class BookCategoryController {

    private final BookCategoryService categoryService;


    @GetMapping("/getall")
    public ResponseEntity<?> getAllValids(){
        return categoryService.getAllValidCategory();
    }


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BookCategory category){
        return categoryService.saveCategory(category);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody BookCategory category){
        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/softdelete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        return categoryService.softDeleteCategory(id);
    }
    
}
