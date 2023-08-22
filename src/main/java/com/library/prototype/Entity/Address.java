package com.library.prototype.Entity;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address_details")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identificationId;
	
	@Column(name = "line_1", nullable = false, updatable = true, length = 100)
	private String addressLine1;
	
	@Column(name = "line_2", nullable = true, updatable = true, length = 100)
	private String addressLine2;
	
	@Column(name = "district", nullable = true, updatable = true, length = 25)
	private String district;
	
	@Column(name = "city", nullable = true, updatable = true, length = 25)
	private String city;
	
	@Column(name = "state", nullable = false, updatable = true, length = 25)
	private String state;
	
	@Column(name = "country", nullable = false, updatable = true, length = 25)
	private String country;
	
	@Column(name = "pincode", nullable = false, updatable = true, length = 6)
	private String pincode;
	
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
