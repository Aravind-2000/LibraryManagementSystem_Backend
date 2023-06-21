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
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department_details")
public class Department {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long randomId;
	
	@Column(name = "department_id", nullable = false, updatable = true, unique = true)
	private String departmentId;

	@Column(name = "department_name", nullable = false, updatable = true)
	private String departmentName;
	
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @CreationTimestamp
	 private LocalDateTime creationDate;

	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @UpdateTimestamp
	 private LocalDateTime updatedDate;
	
	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @Column(name = "valid_flag", nullable = false, updatable = true , columnDefinition = "integer default 1")
	 private int validFlag = 1;
	

}
