package com.library.prototype.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.prototype.Entity.Address;
import com.library.prototype.Service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {


    private final AddressService addressService;


    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return addressService.getAllValidAddress();
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Address address){
        return addressService.saveAddress(address);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Address address){
        return addressService.updateAddress(address);
    }

    @DeleteMapping("/softdelete/{id}")
    public ResponseEntity<?> softDelete(@PathVariable Long id){
        return addressService.softDeleteAddress(id);
    }
    
}
