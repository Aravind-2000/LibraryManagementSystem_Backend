package com.library.prototype.Auth.User;

import java.util.Collection;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.library.prototype.Auth.token.Token;
import com.library.prototype.Entity.Student;
import com.library.prototype.Entity.Teacher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_details")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int randomAutoIncrementedId;

    @Column(name = "user_id", unique = true, nullable = true, updatable = false)
    private String userId;

    @Column(name = "screen_name", nullable = false, length = 100, updatable = true)
    private String screenName;

	/*
	 * @Column(name = "user_first_name", nullable = false, length = 50, updatable =
	 * true) private String firstName;
	 * 
	 * @Column(name = "user_last_name", nullable = false, length = 50, updatable =
	 * true) private String lastName;
	 */
    
    @Column(name = "user_email", unique = true, nullable = false, length = 100, updatable = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false, updatable = true)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Column(name="occupation", nullable = true, updatable = true, length = 10)
    private String occupation;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_student_occupation", unique = false)
    private Student studentObject;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_teacher_occupation", unique = false)
    private Teacher teacherObject;

    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return userEmail;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
