package com.library.prototype.Entity;

import java.time.LocalDateTime;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address_details")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identificationId;
	
	@Column(name = "line_1", nullable = false, length = 100)
	private String addressLine1;
	
	@Column(name = "line_2", length = 100)
	private String addressLine2;
	
	@Column(name = "district", length = 25)
	private String district;
	
	@Column(name = "city", length = 25)
	private String city;
	
	@Column(name = "state", nullable = false, length = 25)
	private String state;
	
	@Column(name = "country", nullable = false, length = 25)
	private String country;
	
	@Column(name = "pincode", nullable = false, length = 6)
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
