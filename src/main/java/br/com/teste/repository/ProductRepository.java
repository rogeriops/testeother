package br.com.teste.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	void deleteByIm(Long id);

	Product findByIm(Long id);
	
	@Modifying
	@Query("delete from Product t where t.im = ?1")
	void deleteModify(Long entityId);

}
