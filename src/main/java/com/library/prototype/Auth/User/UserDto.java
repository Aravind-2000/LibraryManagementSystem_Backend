package com.library.prototype.Auth.User;

import com.library.prototype.Entity.Student;
import com.library.prototype.Entity.Teacher;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userId;
	/*
	 * private String firstName; private String lastName;
	 */
    private String screenName;
    private String email;
    private Role role;
    private String occupation;
    private Student studentObject;
    private Teacher teacherObject;
    
}
