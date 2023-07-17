package com.example.emp.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.emp.model.DatabaseFile;
import com.example.emp.payload.Response;
import com.example.emp.service.DatabaseFileService;

@RestController
public class FileUploadController {

	@Autowired
	private DatabaseFileService fileStorageService;

	@RequestMapping("/hello")
	public String home(HttpServletRequest request) {
		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request).replacePath(null).build().toUriString();
		System.out.println(baseUrl);
		return baseUrl;
	}

	@PostMapping("/uploadFile")
	public Response uploadFile(@RequestParam("files") MultipartFile file) {
		DatabaseFile fileName = fileStorageService.storeFile(file);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/downloadFile/")
				.path(fileName.getFileName())
				.toUriString();

		return new Response(fileName.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
	}

	@GetMapping("/downloadFile/{fileName}")
	public ResponseEntity<DatabaseFile> downloadFile(@PathVariable String fileName) {

		DatabaseFile resource = fileStorageService.getFile(fileName);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFileName() + "\"")
				.body(resource);
	}
	
//	@GetMapping("/files")
//	public ResponseEntity<List<DatabaseFile>> getListFiles() {
//		List<DatabaseFile> files = fileStorageService.getAllFiles().map(dbFile -> {
//			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/files/")
//					.path(dbFile.getId()).toUriString();
//
//			return new ResponseFile(dbFile.getFileName(), fileDownloadUri, dbFile.getFileType(),
//					dbFile.getData().length);
//		}).collect(Collectors.toList());
//
//		return ResponseEntity.status(HttpStatus.OK).body(files);
//	}

	  @GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
	    DatabaseFile fileDB = fileStorageService.getFile(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
	        .body(fileDB.getData());
	  }

	@PostMapping("/uploadMultipleFiles")
	public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		return Arrays.asList(files).stream().map(file -> uploadFile(file)).collect(Collectors.toList());
	}

}