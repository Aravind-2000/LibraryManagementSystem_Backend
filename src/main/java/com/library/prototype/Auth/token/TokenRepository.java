package com.library.prototype.Auth.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
            select t from Token t inner join User u\s
            on t.user.userId = u.userId\s
            where u.userId = :id and (t.expired = false or t.revoked = false)\s
            """)
    List<Token> findAllValidTokenByUser(String id);

    @Query(value = """
            select t from Token t inner join User u\s
            on t.user.userId = u.userId\s
            where u.userId = :id and t.refreshToken = :token\s
            """)
    List<Token> findAllValidTokenByUser(String id, String token);

    Optional<Token> findByToken(String token);

}
