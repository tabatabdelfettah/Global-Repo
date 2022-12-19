package com.globale.book.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.globale.book.base.BaseRepository;
import com.globale.book.entity.Book;

@Repository
public interface BookRepo extends BaseRepository<Book, Long> {
	
	@Override
	@EntityGraph(attributePaths = { "auther"})
	 Optional<Book> findById(Long id); 
	
	@EntityGraph(attributePaths = { "auther"})
	@Override
	 List<Book> findAll() ;
		
	

	@Transactional
	@Modifying
	@Query("delete from Book where auther.id = :id")
	int deleteByAutherId (Long id);
	
}
