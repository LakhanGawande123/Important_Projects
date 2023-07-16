package com.example.emp.service;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
//import java.io.IOException;

import org.springframework.util.StringUtils;

import com.example.emp.exception.FileNotFoundException;
import com.example.emp.exception.FileStorageException;
import com.example.emp.model.DatabaseFile;
import com.example.emp.repo.DatabaseFileRepository;

@Service
public class DatabaseFileService {

	@Autowired
    private DatabaseFileRepository dbFileRepository;

    public DatabaseFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DatabaseFile dbFile = new DatabaseFile(fileName, file.getContentType(), file.getBytes());

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DatabaseFile getFile1(String fileName) {
       DatabaseFile dbFile = dbFileRepository.findByFileName(fileName);
         return dbFile; 
         
    }
    
	public DatabaseFile getFile(String id) {
		return dbFileRepository.findById(id).get();
	}

	public Stream<DatabaseFile> getAllFiles() {
		return dbFileRepository.findAll().stream();
	}
    
}
