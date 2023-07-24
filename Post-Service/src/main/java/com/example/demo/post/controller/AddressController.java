package com.example.demo.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.post.model.Address;
import com.example.demo.post.response.AddressResponse;
import com.example.demo.post.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
    private AddressService addressService;
  
    @GetMapping("/post/{id}")
    public ResponseEntity<AddressResponse> getAddressByEmployeeId(@PathVariable("id") int id) {
        AddressResponse addressResponse = addressService.findAddressByEmployeeId(id);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }
    
    @PostMapping("/post")
    public ResponseEntity<AddressResponse> storeAddress(@RequestBody Address address) {
        AddressResponse addressResponse = addressService.saveAddress(address);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponse);
    }

}