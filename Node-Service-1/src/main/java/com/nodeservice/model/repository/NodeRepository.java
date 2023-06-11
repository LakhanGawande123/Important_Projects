package com.nodeservice.model.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.nodeservice.model.Node;

@Repository
public interface NodeRepository extends JpaRepository<Node, UUID> {
	
	Node findByEmail(String email);

	Node findByToken(String token);
	
	Optional<Node> findById(UUID id);
	
	

	
}
