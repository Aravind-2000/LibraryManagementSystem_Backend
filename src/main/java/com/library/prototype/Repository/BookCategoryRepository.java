package com.library.prototype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.library.prototype.Entity.BookCategory;

public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {

        @Query("""
                        SELECT bc from BookCategory bc where bc.validFlag = 1
                        """)
        List<BookCategory> getAllValidBookCategories();

        @Modifying
        @Transactional
        @Query("""
                        UPDATE BookCategory bc SET bc.categoryName = :name, bc.categoryDescription = :desc\s
                        WHERE bc.randomAutoIncrementedId = :id
                        """)
        void updateBookCategory(String name, String desc, Long id);

        @Modifying
        @Transactional
        @Query("""
                        UPDATE BookCategory bc SET bc.validFlag = 0 WHERE bc.randomAutoIncrementedId = :id
                        """)
        void softDeleteBookCategory(Long id);

}
