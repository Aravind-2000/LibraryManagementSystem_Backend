package com.library.prototype.Auth.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserEmail(String userEmail);
    
    Boolean existsByUserEmail(String userEmail);
    
}
