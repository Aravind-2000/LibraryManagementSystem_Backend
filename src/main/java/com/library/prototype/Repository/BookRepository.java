package com.library.prototype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.library.prototype.Entity.Books;

public interface BookRepository extends JpaRepository<Books, Integer>{


    @Query( """
            SELECT b from Books b where b.validFlag = 1
            """)
    List<Books> getAllValidBooks();


    Books getBooksByBookId(String bookId);


    @Modifying
    @Transactional
    @Query("""
            UPDATE Books b SET b.bookName = :name, b.bookDescription = :desc, b.bookCount = :count,
            b.bookCategoryId = :bookcatid, b.bookPicture = :propic WHERE b.bookId = :bookid
            """)
    void updateBook(String bookid, String name, String desc, int count, Long bookcatid, String propic);


    @Modifying
    @Transactional
    @Query("""
            UPDATE Books b SET b.validFlag = 0 WHERE b.bookId = :id
            """)
    void softDeleteBook(String id);

}
