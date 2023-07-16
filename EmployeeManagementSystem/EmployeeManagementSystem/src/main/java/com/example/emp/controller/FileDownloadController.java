package com.example.emp.controller;

//import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp.service.DatabaseFileService;

@RestController
public class FileDownloadController {
	
	@Autowired
    private DatabaseFileService fileStorageService;

//    @GetMapping("/downloadFile/{fileName:.+}")
//    public ResponseEntity < Resource > downloadFile(@PathVariable String fileName, HttpServletRequest request) {
//        // Load file as Resource
//        DatabaseFile databaseFile = fileStorageService.getFile(fileName);
//
//        return ResponseEntity.ok()
//            .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
//            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
//            .body(new ByteArrayResource(databaseFile.getData()));
//    }

}
