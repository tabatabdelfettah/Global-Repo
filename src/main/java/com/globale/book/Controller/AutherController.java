package com.globale.book.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globale.book.Service.AutherService;
import com.globale.book.entity.Auther;
import com.globale.book.entity.AutherSearch;
import com.globale.book.entity.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/auther")
public class AutherController {

	@Autowired
	AutherService autherService;

	@Operation(summary = "Get a book by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the book", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Book.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Book not found", content = @Content) })
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		return ResponseEntity.ok(autherService.findById(id));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok(autherService.findAll());
	}

	@PostMapping()
	public ResponseEntity<?> insert(@RequestBody @Valid Auther entity) {

		return ResponseEntity.ok(autherService.insert(entity));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody @Valid Auther entity) {

		return ResponseEntity.ok(autherService.update(entity));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {

		autherService.deleteById(id);
		return ResponseEntity.ok(null);
	}
	@PostMapping("/spec")
	public ResponseEntity<?> findByAutherSpec(@RequestBody AutherSearch search) {
	   
			return  ResponseEntity.ok(autherService.findByAutherSpec(search));
		}

}
