package com.globale.book.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.globale.book.Service.AutherService;
import com.globale.book.Service.BookService;
import com.globale.book.entity.Auther;
import com.globale.book.entity.Book;

@Component
public class StarUpApp  implements CommandLineRunner{
	@Autowired
	private AutherService autherService;
	
	@Autowired
	private BookService bookService;
	
@Override
public void run(String... args) throws Exception {
	// TODO Auto-generated method stub
	// adding some data for authers
//			if (autherService.findAll().isEmpty()) {
			Auther auther1 = new Auther();
			auther1.setName("ALi");

			Auther auther2 = new Auther();
			auther2.setName("Mohamed");

			Auther auther3 = new Auther();
			auther3.setName("Ahmed");

			autherService.insertAll(Arrays.asList(auther1, auther2, auther3));
//		}
			// adding some data for books
//			if(bookService.findAll().isEmpty()) {
			Book book = new Book();
			book.setName("Java JPA");
			book.setPrice(200.5);
			book.setAuther(autherService.getOne(1L));

			Book book2 = new Book();
			book2.setName("Data Base Mysql");
			book2.setPrice(300.5);
			book2.setAuther(autherService.getOne(1L));

			Book book3 = new Book();
			book3.setName("Python");
			book3.setPrice(120.5);
			book3.setAuther(autherService.getOne(2L));


			bookService.insertAll(Arrays.asList(book,book2, book3));
//			}
}
}
