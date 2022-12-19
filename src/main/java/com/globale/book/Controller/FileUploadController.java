package com.globale.book.Controller;

import java.util.Arrays;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.globale.book.Service.FileUploadService;

@RestController
@RequestMapping("/file")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping("/upload")
	public ResponseEntity<Object> uploadFile(@RequestParam Long id, @RequestParam String pathType,
			@RequestParam MultipartFile file) {

		String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType);

		return ResponseEntity.ok(fileName);
	}
	@PostMapping("/multipleFiles")
	public ResponseEntity<Object> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
			@RequestParam("id") Long id, @RequestParam String pathType) {
		 Arrays.asList(files).stream().map(file -> uploadFile(id, pathType, file)).collect(Collectors.toList());
		 
		 return ResponseEntity.ok(null);
	}

}
