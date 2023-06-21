package com.library.prototype.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teacher_details")
public class Teacher {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int randomId;
	
	@Column(name = "teacher_identity_number", updatable = true, nullable = false, unique = true, length = 10)
	private String teacherId;
	
	@Column(name = "first_name", updatable = true, nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "last_name", updatable = true, nullable = false, length = 50)
	private String lastName;
	
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
	 @Column(name = "valid_flag", nullable = false, updatable = true, columnDefinition = "integer default 1")
	 private int validFlag = 1;

}
