package com.library.prototype.Entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books_borrowed_details")
public class BooksBorrowed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long incId;

    @Column(name = "user_email", nullable = false, updatable = false)
    private String user;


    @Column(nullable = false, name = "borrowed_book_id")
    private String bookId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "borrowed_book_id", referencedColumnName = "book_id", insertable = false, updatable = false)
    private Books borrowedBook;


    @Column(name = "book_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;


    @Column(name = "book_due_date", nullable = false)
    private LocalDate dueDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @JsonIgnore
    @Column(name = "valid_flag", nullable = false)
    private int validFlag = 1;

}
