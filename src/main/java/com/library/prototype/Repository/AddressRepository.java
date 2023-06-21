package com.library.prototype.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.library.prototype.Entity.Address;


public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	/*
	 * @Query(value =
	 * " SELECT * FROM address_details where valid_flag = 1 ",nativeQuery = true)
	 * List<Address> getAllValidAddresses();
	 */
	
	
	@Query(value = """ 
				select a from Address a where a.validFlag = 1
			""")
	List<Address> getAllValidAddresses();
	
	
	@Modifying
	@Transactional
	@Query(value = """
				UPDATE Address a SET a.addressLine1 = :line1, a.addressLine2 = :line2, 
				a.district = :district, a.city = :city, a.state = :state,  a.country = :country, 
				a.pincode = :pin WHERE a.identificationId = :id
			""")
	void updateAddress(Long id, String line1, String line2, String district, String city, String state, String country, String pin );
	
	
	@Modifying
	@Transactional
	@Query(value = """
				UPDATE Address a SET a.validFlag = 0 WHERE a.identificationId = :id
			""")
	void softDeleteAddress(Long id);
	

}
