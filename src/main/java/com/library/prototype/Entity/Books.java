package com.library.prototype.Entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="books_details")
public class Books {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long randomAutoIncrementedId;
	
	@Column(name = "book_id", nullable = false, unique = true, updatable = false)
	private String bookId;
	
	@Column(name="book_name", nullable = false, length = 30)
	private String bookName;
	
	@Column(name="book_description", nullable = false, length = 200)
	private String bookDescription;
	
	@Column(name = "book_count", nullable = false)
	private Integer bookCount;

	@Column(name = "book_category_id", nullable = false)
	private Long bookCategoryId;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "book_category_id", insertable = false, updatable = false)
	private BookCategory bookCategory;
	
	@Lob
	@Column(name = "book_picture")
	private String bookPicture;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@CreationTimestamp
	private LocalDateTime creationDate;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@UpdateTimestamp
	private LocalDateTime updatedDate;
	 
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "valid_flag", nullable = false)
	private int validFlag = 1;

	

}
