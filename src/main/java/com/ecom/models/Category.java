package com.ecom.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

	private String name;
	@OneToMany(mappedBy = "category" ,cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	List<Product> products;

	@Override
	public String toString() {
		return "Category [name=" + name + ", products = " + products + "]";
	}
	
}
