package com.library.prototype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.library.prototype.Entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	@Query(value = """
			SELECT s FROM Student s where s.validFlag = 1
			""")
	List<Student> getAllValidStudents();

	@Query(value = "INSERT into student_details (student_identity_number, first_name, last_name, department_id, fk_address_id) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
	void addStudent(String teacherId, String firstName, String lastName, Long departmentId, int fk_address_id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE student_details SET first_name = :fn, last_name = :ln, fk_address_id = :addfkid," +
			"department_id = :dpt WHERE student_identity_number = :sdn", nativeQuery = true)
	void updateStudent(String sdn, String fn, String ln, Long addfkid, Long dpt);

	@Modifying
	@Transactional
	@Query(value = "UPDATE student_details SET valid_flag = 0 WHERE student_identity_number = :sdn", nativeQuery = true)
	void softDeleteStudent(String sdn);

}
