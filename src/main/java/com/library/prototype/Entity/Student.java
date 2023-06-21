package com.library.prototype.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_details")
public class Student {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long randomId;
	
	@Column(name = "student_identity_number", updatable = true, nullable = false, unique = true, length = 10)
	private String studentId;
	
	@Column(name = "first_name", updatable = true, nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", updatable = true, nullable = false, length = 50)
	private String lastName;
	
	@Column(name ="deptId", updatable = true, nullable = true)
	private Long deptId;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "deptId", updatable = false, insertable = false)
	private Department department;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_address_id")
	private Address address;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @CreationTimestamp
	 private LocalDateTime creationDate;

	 @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	 @UpdateTimestamp
	 private LocalDateTime updatedDate;
	
	 @JsonIgnore
	 @Column(name = "valid_flag", nullable = false, updatable = true)
	 private int validFlag = 1;
	
	
	

}
