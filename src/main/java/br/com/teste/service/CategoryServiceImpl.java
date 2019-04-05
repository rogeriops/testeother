package br.com.teste.service;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.domain.Category;
import br.com.teste.repository.CategoryRepository;


@Service
@Transactional
@Qualifier("category")
public class CategoryServiceImpl implements CategoryService {
	
	public static Logger LOGGER = Logger.getLogger(CategoryServiceImpl.class.getName());
	
	public CategoryRepository categoryRepository;
		
	
	@Autowired
	public CategoryServiceImpl( CategoryRepository categoryRepository) {
		this.categoryRepository=categoryRepository;
	}
	
	
	
	@Override
	public ResponseEntity<?> findAllCategory() {
		
	    try {
	    	
	    	List<Category> list = categoryRepository.findAll();

	    	if(list.isEmpty()) {
	    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    	}
	    	return new ResponseEntity<>(categoryRepository.findAll(),  HttpStatus.OK);
	  
		} catch (Exception ex) {
	    	return new ResponseEntity<>(ex.getMessage(),  HttpStatus.BAD_REQUEST);
		}
	}

	
	
}
