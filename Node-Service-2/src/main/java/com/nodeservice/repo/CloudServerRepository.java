package com.nodeservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nodeservice.model.CloudServer;

@Repository
public interface CloudServerRepository extends JpaRepository<CloudServer, String> {

}
