package com.library.prototype.Service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.*;

import com.library.prototype.Entity.Address;
import com.library.prototype.Entity.GlobalResponse;
import com.library.prototype.Repository.AddressRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;


    public ResponseEntity<?> getAllValidAddress(){
        try{
            List<Address> addresses = addressRepository.getAllValidAddresses();
            if(addresses.isEmpty()){
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            else{
                return ResponseEntity.ok().body(addresses);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> saveAddress(Address address){
        try{
            if(address == null || (address.getAddressLine1() == null || address.getState() == null 
                            || address.getCountry() == null || address.getPincode() == null)){
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            }
            else{
                addressRepository.save(address);
                var res = GlobalResponse.builder().responseData(address).httpStatus(HttpStatus.OK).build();
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> updateAddress(Address address){
        try{
            if(address == null || address.getIdentificationId() == null){
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            }
            else{
                addressRepository.updateAddress(address.getIdentificationId(), address.getAddressLine1(), address.getAddressLine2(), 
                        address.getDistrict(), address.getCity(), address.getState(), address.getCountry(), address.getPincode());
                var res = GlobalResponse.builder().responseData(address).httpStatus(HttpStatus.OK).build();
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> softDeleteAddress(Long id){
        try{
            if(id == null){
                return new ResponseEntity<>("SOME DATA IS MISSING", HttpStatus.BAD_REQUEST);
            }
            else{
                addressRepository.softDeleteAddress(id);
                return new ResponseEntity<>("DATA DELETED SUCCESSFULLY", HttpStatus.OK);
            }
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    
}
