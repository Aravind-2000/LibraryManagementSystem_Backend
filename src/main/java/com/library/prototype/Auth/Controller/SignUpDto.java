package com.library.prototype.Auth.Controller;

import com.library.prototype.Entity.Student;
import com.library.prototype.Entity.Teacher;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String screenName;
    private String userEmail;
    private String password;
    private String occupation;
    private Student student;
    private Teacher teacher;

}
