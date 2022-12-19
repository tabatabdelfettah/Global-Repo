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

import com.globale.book.Service.BookService;
import com.globale.book.entity.Book;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {

		return ResponseEntity.ok( bookService.findById(id));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() {

		return ResponseEntity.ok( bookService.findAll());
	}

	@PostMapping()
	public ResponseEntity<?> insert(@RequestBody @Valid Book entity) {

		return ResponseEntity.ok( bookService.insert(entity));
	}

	@PutMapping()
	public ResponseEntity<?> update(@RequestBody @Valid Book entity) {

		return ResponseEntity.ok( bookService.update(entity));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {

		 bookService.deleteById(id);
		return ResponseEntity.ok(null);
	}
}
