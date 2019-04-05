package br.com.teste.service;

import org.springframework.http.ResponseEntity;

import br.com.teste.domain.Product;

public interface ProductService {

	ResponseEntity<?> deleteProduct(Long id);

	ResponseEntity<?> updateProduct(Product product);

	ResponseEntity<?> findProductById(Long id);

}