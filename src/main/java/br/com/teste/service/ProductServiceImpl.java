package br.com.teste.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.teste.domain.Category;
import br.com.teste.domain.Product;
import br.com.teste.repository.CategoryRepository;
import br.com.teste.repository.ProductRepository;


@Service
@Transactional
@Qualifier("product")
public class ProductServiceImpl implements ProductService {
	
	public static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());
	
	public ProductRepository productRepository;
	
	
	@Autowired
	public ProductServiceImpl( ProductRepository productRepository) {
		this.productRepository=productRepository;
	}
	
	/* (non-Javadoc)
	 * @see br.com.teste.service.ProductService#deleteProduct(java.lang.Long)
	 */
	@Override
	@Transactional
	public ResponseEntity<?> deleteProduct (Long id) {
		
	    try {
	    	
	    	
	    	Product product = productRepository.findByIm(id);

			if (product == null ) {
				return new ResponseEntity<>("Produto não encontrado",HttpStatus.NOT_FOUND);
			}

			
			productRepository.deleteModify(id);//(product.get());
			
			

			LOGGER.info("Product was delete");
		    
			return new ResponseEntity<>("Produto deletado com sucesso.",HttpStatus.OK);
	    	
	    		
		} catch (Exception ex) {
	    	return new ResponseEntity<>(ex.getMessage(),  HttpStatus.BAD_REQUEST);
		}
	}
	

	/* (non-Javadoc)
	 * @see br.com.teste.service.ProductService#updateProduct(br.com.teste.domain.Product)
	 */
	@Override
	public ResponseEntity<?> updateProduct (Product product) {
		
	    try {
	    	
	    	Optional<Product> productO = productRepository.findById(product.getIm());

			if (productO == null) {
				LOGGER.info("Product not found.");
				return new ResponseEntity<>("Produto não encontrado",HttpStatus.NOT_FOUND);
			}
	    	
			product.setCategory(productO.get().getCategory());
	    	productRepository.save(product);
	    	
	    	LOGGER.info("Product was update.");
	    	
	    	return new ResponseEntity<>("Atualizado com sucesso",  HttpStatus.OK);
	  
		} catch (Exception ex) {
	    	return new ResponseEntity<>(ex.getMessage(),  HttpStatus.BAD_REQUEST);
		}
	}

	

	/* (non-Javadoc)
	 * @see br.com.teste.service.ProductService#findProductById(java.lang.Long)
	 */
	@Override
	public ResponseEntity<?> findProductById(Long id) {
		
	    try {
	    	LOGGER.info("Product was confered.");
	    	
	    	return new ResponseEntity<>(productRepository.findById(id),  HttpStatus.OK);
	  
		} catch (Exception ex) {
	    	return new ResponseEntity<>(ex.getMessage(),  HttpStatus.BAD_REQUEST);
		}
	}

}
