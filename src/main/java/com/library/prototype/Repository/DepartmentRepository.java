package com.library.prototype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.library.prototype.Entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

        @Query("""
                        SELECT d from Department d where d.validFlag = 1
                        """)
        List<Department> getAllValidDepartments();

        @Modifying
        @Transactional
        @Query("""
                        UPDATE Department d SET d.departmentId = :depId, d.departmentName = :name WHERE d.randomId = :id
                        """)
        void updateDepartment(String depId, String name, Long id);

        @Modifying
        @Transactional
        @Query("""
                        UPDATE Department d SET d.validFlag = 0 WHERE d.randomId = :id
                        """)
        void softDeleteDepartment(Long id);

}
