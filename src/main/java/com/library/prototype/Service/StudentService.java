package com.library.prototype.Service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.library.prototype.Entity.GlobalResponse;
import com.library.prototype.Entity.Student;
import com.library.prototype.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public ResponseEntity<?> getAllStudents() {
        try {
            List<Student> students = studentRepository.getAllValidStudents();
            if(students.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else{
                // var res = GlobalResponse.builder()
                // .responseData(students)
                // .httpStatus(HttpStatus.OK);
                // return ResponseEntity.ok().body(res);
                return new ResponseEntity<>(students, HttpStatus.OK);
            }
        }
        catch (Error e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> saveStudent(Student student){

        try{
            if(student == null || (student.getFirstName() == null || student.getLastName() == null || student.getStudentId() == null ||
                student.getDeptId() == null || student.getAddress() == null)){
                    return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
                }  
            else{
                student.setValidFlag(1);
                studentRepository.save(student);
                var res = GlobalResponse.builder()
                .responseData(student)
                .httpStatus(HttpStatus.OK).build();
                return ResponseEntity.ok().body(res);
            }
        }
        catch(Error e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);        }
    }


    public ResponseEntity<?> updateStudent(Student student){
        try{
            if(student == null || (student.getFirstName() == null || student.getLastName() == null || student.getStudentId() == null ||
                student.getDeptId() == 0 || student.getAddress() == null)){
                    return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
                }
            else{
                studentRepository.updateStudent(student.getStudentId(), student.getFirstName(), student.getLastName(), 
                    student.getAddress().getIdentificationId(), student.getDeptId());
                var res = GlobalResponse.builder()
                .responseData(student)
                .httpStatus(HttpStatus.OK).build();
                return ResponseEntity.ok().body(res);
            }
        }
        catch(Error e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);       
        }
    }


    public ResponseEntity<?> softDeleteStudent(String studentId){
        try{
            if(studentId == null ){
                    return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
                }
            else{
                studentRepository.softDeleteStudent(studentId);
                return new ResponseEntity<>("DELETED SUCCESSFULLY", HttpStatus.OK);
            }
        }
        catch(Error e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);       
        }
    }
    



    
}
