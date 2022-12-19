package com.globale.book.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globale.book.base.BaseService;
import com.globale.book.entity.Book;
import com.globale.book.repository.BookRepo;

@Service
public class BookService extends BaseService<Book, Long> {

	@Autowired
	private BookRepo bookRepo;

	@Override
	public Book update(Book entity) {
		Book book = findById(entity.getId());
		book.setName(entity.getName());
		return super.update(book);
	}

	public int deleteByAutherId(Long id) {

		return bookRepo.deleteByAutherId(id);
	}
}
//
//	
//
//	public Book findById(Long id) {
//
//		return bookRepo.findById(id).orElseThrow();
//	}
//
//	public List<Book> findAll() {
//
//		return bookRepo.findAll();
//	}
//
//	public Book insert(Book entity) {
//		if (entity.getId() != null) {
//			throw new RuntimeException();
//		}
//
//		return bookRepo.save(entity);
//	}
//	public List<Book>  insertAll(List<Book> entity) {
//		
//		return bookRepo.saveAll(entity);
//	}
//
//	public Book update(Book entity) {
//		Book book = findById(entity.getId());
//		book.setName(entity.getName());
//		return bookRepo.save(book);
//	}
//
//	public void deleteById(Long id) {
//
//		bookRepo.deleteById(id);
//	}
