package com.example.demo.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.post.model.Address;


@Repository
public interface AddressRepo extends JpaRepository<Address, Integer> {

}
