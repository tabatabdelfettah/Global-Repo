package com.globale.book.Service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globale.book.base.BaseService;
import com.globale.book.entity.Auther;
import com.globale.book.entity.AutherSearch;
import com.globale.book.error.DaplicateRecoredException;
import com.globale.book.repository.AutherRepo;
import com.globale.book.repository.AutherSpec;

@Service
public class AutherService extends BaseService<Auther, Long> {
//
	@Autowired
	private AutherRepo autherRepo;
	private Logger log=LoggerFactory.getLogger(AutherService.class);

	@Override
//	@CacheEvict(value = {"auther"} , key ="#root.methodName", allEntries = true)
	public Auther insert(Auther entity) {
		
	if (!entity.getEmail().isEmpty() && entity.getEmail() != null) {
		Optional<Auther> auther = findByEmail(entity.getEmail());

	
		log.info("author name is {} and email is {} " , entity.getName() , entity.getEmail());
				
		if(auther.isPresent()) {
			
			log.error("This email already found with anther author");
			throw new DaplicateRecoredException("This email already found with anther author ): ");
		}

	}

		return super.insert(entity);
	}
	

	@Override
	public Auther update(Auther entity) {
		Auther auther = findById(entity.getId());
		auther.setName(entity.getName());
		return super.update(auther);
	}

	public List<Auther> findByAutherSpec(AutherSearch search) {
		AutherSpec aut = new AutherSpec(search);
		return autherRepo.findAll(aut);
	}
	
	
	
	public Optional<Auther> findByEmail(String email) {

		return autherRepo.findByEmail(email);
	}
//
//	public Auther findById(Long id) {
//
//		return autherRepo.findById(id).orElseThrow();
//	}
	public Auther getOne(Long id) {

		return autherRepo.getReferenceById(id);
	}
//
//	public List<Auther> findAll() {
//
//		return autherRepo.findAll();
//	}
//
//	public Auther insert(Auther entity) {
//		if (entity.getId() != null) {
//			throw new RuntimeException();
//		}
//
//		return autherRepo.save(entity);
//	}
//	public List<Auther>  insertAll(List<Auther> entity) {
//	
//		return autherRepo.saveAll(entity);
//	}
//
//	public Auther update(Auther entity) {
//		Auther auther = findById(entity.getId());
//		auther.setName(entity.getName());
//		return autherRepo.save(auther);
//	}
//
//	public void deleteById(Long id) {
//
//		autherRepo.deleteById(id);
//	}

}
