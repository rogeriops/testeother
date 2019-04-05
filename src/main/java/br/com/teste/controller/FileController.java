package br.com.teste.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.teste.domain.Product;
import br.com.teste.payload.UploadFileResponse;
import br.com.teste.service.CategoryService;
import br.com.teste.service.FileStorageService;
import br.com.teste.service.MQStatusService;
import br.com.teste.service.ProductService;

@RestController	
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	
	 @Autowired
	 private FileStorageService fileStorageService;
	 
	 @Autowired
	 private CategoryService categoryService;
	 
	 @Autowired
	 private ProductService productService;
	 
	 @Autowired
	 private MQStatusService mQStatusService;
	 
	 @PostMapping("/uploadFile")
	 public  ResponseEntity<UploadFileResponse> uploadFile(@RequestParam("file") MultipartFile file) {

	        return fileStorageService.storeFile(file);
	 }
	 
	 @GetMapping("/")
	 public  ResponseEntity<?> findAll() {

	        return categoryService.findAllCategory();
	 }
	 
	 @GetMapping("/product/{id}")
	 public  ResponseEntity<?> findProductById(@PathVariable("id") long id) {

	        return productService.findProductById(id);
	 }
	 
	 @DeleteMapping("/product/{id}")
	 public  ResponseEntity<?> deleteProductById(@PathVariable("id") long id) {

	        return productService.deleteProduct(id);
	 }
	 
	 @PostMapping("/product")
	 public  ResponseEntity<?> updateProduct(@RequestBody Product product) {

	        return productService.updateProduct(product);
	 }
	
	 @GetMapping("/status/{fileName}")
	 public  ResponseEntity<?> findStatus(@PathVariable("fileName") String fileName) {

	        return mQStatusService.findAllStatus(fileName);
	 }
	 
	 
	 
}
