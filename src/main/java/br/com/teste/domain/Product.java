package br.com.teste.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreRemove;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Product {
	
	@Id
	private long im;
	private String name;
	private int free_shipping;
	private String description;
	private Double price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_category")
	@JsonBackReference
	private Category category;
	
	public Product(long im) {
		
		this.im = im;
	}


	public Product(long im, String name, int free_shipping, String description, double price, Category category) {
		this.im = im;
		this.name = name;
		this.free_shipping = free_shipping;
		this.description = description;
		this.price = price;
		this.category=category;
	}
	
	
	
	public Product() {
		
	}

	public long getIm() {
		return im;
	}
	public void setIm(long im) {
		this.im = im;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFree_shipping() {
		return free_shipping;
	}
	public void setFree_shipping(int free_shipping) {
		this.free_shipping = free_shipping;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + (int) (im ^ (im >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (im != other.im)
			return false;
		return true;
	}



	
	


}
