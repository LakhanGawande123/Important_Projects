package com.example.demo.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.exception.VideoAlreadyExistsException;
import com.example.demo.exception.VideoNotFoundException;
import com.example.demo.model.Video;
import com.example.demo.repository.VideoRepo;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoRepo repo;

    @Override
    public Video getVideo(String name) {
        if(!repo.existsByName(name)){
                throw new VideoNotFoundException();
        }
        return repo.findByName(name);
    }

    @Override
    public List<String> getAllVideoNames() {
            return repo.getAllEntryNames();
    }

    @Override
    public void saveVideo(MultipartFile file, String name) throws IOException {
        if(repo.existsByName(name)){
                throw new VideoAlreadyExistsException();
        }
        Video newVid = new Video(name, file.getBytes());
        repo.save(newVid);
    }

}
