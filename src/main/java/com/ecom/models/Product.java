package com.ecom.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Product extends BaseModel {


	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", description=" + description + ", category= " + category.getName() 
				+ ", imageUrl=" + imageUrl + "]";
	}
	private String name;
	private double price;
	private String description;

	@ManyToOne
	private Category category;
	private String imageUrl;
}
