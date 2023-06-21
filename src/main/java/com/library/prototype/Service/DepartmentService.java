package com.library.prototype.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.prototype.Entity.Department;
import com.library.prototype.Entity.GlobalResponse;
import com.library.prototype.Repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {


    private final DepartmentRepository departmentRepository;


    public ResponseEntity<?> getAllDepartments(){
        try{
            List<Department> deptList = departmentRepository.getAllValidDepartments();
            if(deptList.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else{
                return ResponseEntity.ok().body(deptList);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> addDepartment(Department dept){
        try{
            if(dept == null || (dept.getDepartmentId() == null || dept.getDepartmentName() == null)){
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.NO_CONTENT);
            }
            else{
                dept.setValidFlag(1);
                departmentRepository.save(dept);
                var res = GlobalResponse.builder()
                            .responseData(dept).httpStatus(HttpStatus.OK).build();
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    } 
    
}
