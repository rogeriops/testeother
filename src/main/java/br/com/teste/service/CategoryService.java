package br.com.teste.service
;

import org.springframework.http.ResponseEntity;

import br.com.teste.domain.Category;
import br.com.teste.domain.Product;


public interface CategoryService {
	
	ResponseEntity<?> findAllCategory();

}
