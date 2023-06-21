package com.library.prototype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.library.prototype.Entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	
	@Query(value = " SELECT * FROM teacher_details where valid_flag = 1 ",nativeQuery = true)
	List<Teacher> getAllValidTeacher();
	
	@Query(value = "INSERT into teacher_details (teacher_identity_number, first_name, last_name, fk_address_id, valid_flag) VALUES (?1,?2,?3,1)"
			, nativeQuery = true )
	void addTeacher(String teacherId, String firstName, String lastName, int fk_address_id,  String validFlag);
	
	@Modifying
    @Transactional
	@Query(value = "UPDATE teacher_details SET first_name = :fn, last_name = :ln, fk_address_id = :addfkid"
	 + " WHERE teacher_identity_number = :tdn  ", nativeQuery = true)
	void updateTeacher(String tdn, String fn, String ln, Long addfkid);
	
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE teacher_details SET valid_flag = 0 WHERE teacher_identity_number = :tdn", nativeQuery = true)
    void softDeleteTeacher(String tdn);
	
	
	

}
