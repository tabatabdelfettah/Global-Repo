package com.globale.book.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.globale.book.base.BaseRepository;
import com.globale.book.entity.Auther;

@Repository
public interface AutherRepo  extends BaseRepository<Auther, Long> ,JpaSpecificationExecutor<Auther>{
	
	Optional<Auther> findByEmail(String email);


}
