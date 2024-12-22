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

import com.library.prototype.Entity.Student;
import com.library.prototype.Service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/getallstudents")
    public ResponseEntity<?> getAll() {
        return studentService.getAllStudents();
    }

    @PostMapping("/savestudent")
    public ResponseEntity<?> save(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @PutMapping("/updatestudent")
    public ResponseEntity<?> update(@RequestBody Student student) {
        return studentService.updateStudent(student);
    }

    @DeleteMapping("/softdeletestudent/{sdn}")
    public ResponseEntity<?> softDelete(@PathVariable String sdn) {
        return studentService.softDeleteStudent(sdn);
    }

}
