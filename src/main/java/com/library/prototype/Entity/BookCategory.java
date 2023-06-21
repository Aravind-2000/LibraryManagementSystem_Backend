package com.library.prototype.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name="books_category_details")
public class BookCategory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long randomAutoIncrementedId;
	
	@Column(name = "category_name", nullable = false, updatable = true, length = 50)
	private String categoryName;
	
	@Column(name = "category_description", nullable = false, updatable = true, length = 100)
	private String categoryDescription;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @CreationTimestamp
	 private LocalDateTime creationDate;

	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @UpdateTimestamp
	 private LocalDateTime updatedDate;
	
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @Column(name = "valid_flag", nullable = false, updatable = true, columnDefinition = "integer default 1")
	 private int validFlag = 1;


}
