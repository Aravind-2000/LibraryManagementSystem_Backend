package com.library.prototype.Auth.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserEmail(String userEmail);
    
    Boolean existsByUserEmail(String userEmail);

    @Query(value = """
            SELECT u FROM User u WHERE u.userEmail = :email
            """)
    User findUserByEmail(String email);
    
}
