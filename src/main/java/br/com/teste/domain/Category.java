package br.com.teste.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Category {
	
	@Id
	private long id;

	@JsonManagedReference
	@OneToMany(mappedBy = "category",fetch = FetchType.EAGER, orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	private List<Product> products;
	
	
	
	public Category() {
		super();
	}


	public Category(long id) {
		this.id= id;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public List<Product> getProducts() {
		return products;
	}


	public void setProducts(List<Product> products) {
		this.products = products;
	}

	
	

}

