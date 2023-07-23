package com.library.prototype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.library.prototype.Entity.BooksBorrowed;
import org.springframework.transaction.annotation.Transactional;

public interface BorrowedBooksRepository extends JpaRepository<BooksBorrowed, Long> {


    @Query(value = """
            SELECT bb from BooksBorrowed bb WHERE bb.validFlag = 1
            """)
    List<BooksBorrowed> getAllValidBooks();

    @Query(value = """
            SELECT bb FROM BooksBorrowed bb WHERE bb.user = :userValue
            """)
    List<BooksBorrowed> getBooksBorrowedByUser(String userValue);

    @Query(value = """
            SELECT bb FROM BooksBorrowed bb LEFT JOIN Books bs ON bb.bookId = bs.bookId WHERE bs.bookName = :nameValue AND bb.user = :userValue
            """)
    List<BooksBorrowed> getBooksBorrowedByBookName(String nameValue, String userValue);

    @Query(value = """
            SELECT bb FROM BooksBorrowed bb LEFT JOIN Books bs ON bb.bookId = bs.bookId WHERE bs.bookId = :idValue AND bb.user = :userValue
            """)
    List<BooksBorrowed> getBooksBorrowedByBookId(String idValue, String userValue);


    @Modifying
    @Transactional
    @Query(value = """
            UPDATE BooksBorrowed bb SET bb.bookStatus = 'RETURNED' WHERE bb.bookId = :book
            """)
    void changeBookStatus(String book);

    @Query(value = """
            SELECT bb FROM BooksBorrowed bb WHERE bb.dueDate < DATE_FORMAT(CURRENT_DATE(), '%d/%m/%y')
            """)
    List<BooksBorrowed> getBooksByDatePassed();
}
