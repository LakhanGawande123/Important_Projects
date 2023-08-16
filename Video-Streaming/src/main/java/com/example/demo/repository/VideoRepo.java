package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Video;

public interface VideoRepo extends JpaRepository<Video, Long> {

	@Query("SELECT v FROM Video v WHERE v.name = :name")
	Video findByName(@Param("name") String name);

    boolean existsByName(String name);

    @Query(nativeQuery = true, value="SELECT name FROM video")
    List<String> getAllEntryNames();
}
