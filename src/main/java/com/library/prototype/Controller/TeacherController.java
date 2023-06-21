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

import com.library.prototype.Entity.Teacher;
import com.library.prototype.Service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return teacherService.getAllTeachers();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Teacher teacher){
        return teacherService.saveTeacher(teacher);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Teacher teacher){
        return teacherService.updateStudent(teacher);
    }

    @DeleteMapping("/softdelete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable String id){
        return teacherService.softDeleteTeacher(id);
    }
    
}
