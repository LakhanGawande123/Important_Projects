package com.example.demo.post.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.post.model.Address;
import com.example.demo.post.repository.AddressRepo;
import com.example.demo.post.response.AddressResponse;

@Service
public class AddressService {
	
	@Autowired
    private AddressRepo addressRepo;
  
    @Autowired
    private ModelMapper mapper;
  
    public AddressResponse findAddressByEmployeeId(int id) {
        Optional<Address> addressById = addressRepo.findById(id);
        AddressResponse addressResponse = mapper.map(addressById, AddressResponse.class);
        return addressResponse;
    }
    
    public AddressResponse saveAddress(Address address) {
        Address address1 = addressRepo.save(address);
        AddressResponse addressResponse = mapper.map(address1, AddressResponse.class);
        return addressResponse;
    }
    
}