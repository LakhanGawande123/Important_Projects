package com.example.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.emp.model.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

	Document findByDocName(String fileName);

}
