package com.library.prototype.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.prototype.Entity.Department;
import com.library.prototype.Service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService deptService;



    @GetMapping("/getalldepts")
    public ResponseEntity<?> getAll(){
        return deptService.getAllDepartments();
    }

    @PostMapping("/savedepartments")
    public ResponseEntity<?> save(@RequestBody Department dept){
        return deptService.addDepartment(dept);
    }
    
}
