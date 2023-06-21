package com.library.prototype.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.prototype.Entity.GlobalResponse;
import com.library.prototype.Entity.Teacher;
import com.library.prototype.Repository.TeacherRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;


    public ResponseEntity<?> getAllTeachers() {
        try {
            List<Teacher> teachers = teacherRepository.getAllValidTeacher();
            if(teachers.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else{
                // var res = GlobalResponse.builder()
                // .responseData(students)
                // .httpStatus(HttpStatus.OK);
                // return ResponseEntity.ok().body(res);
                return new ResponseEntity<>(teachers, HttpStatus.OK);
            }
        }
        catch (Error e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> saveTeacher(Teacher teacher){

        try{
            if(teacher == null || (teacher.getFirstName() == null || teacher.getLastName() == null || teacher.getTeacherId() == null)){
                    return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
                }  
            else{
                teacherRepository.save(teacher);
                var res = GlobalResponse.builder()
                .responseData(teacher)
                .httpStatus(HttpStatus.OK).build();
                return ResponseEntity.ok().body(res);
            }
        }
        catch(Error e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);        }
    }


    public ResponseEntity<?> updateStudent(Teacher teacher){
        try{
            if(teacher == null || teacher.getTeacherId() == null){
                    return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
                }
            else{
                teacherRepository.updateTeacher(teacher.getTeacherId(), teacher.getFirstName(), teacher.getLastName(), 
                    teacher.getAddress().getIdentificationId());
                var res = GlobalResponse.builder()
                .responseData(teacher)
                .httpStatus(HttpStatus.OK).build();
                return ResponseEntity.ok().body(res);
            }
        }
        catch(Error e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);       
        }
    }


    public ResponseEntity<?> softDeleteTeacher(String teacherId){
        try{
            if(teacherId == null ){
                    return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
                }
            else{
                teacherRepository.softDeleteTeacher(teacherId);
                return new ResponseEntity<>("DELETED SUCCESSFULLY", HttpStatus.OK);
            }
        }
        catch(Error e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);       
        }
    }
    
}
