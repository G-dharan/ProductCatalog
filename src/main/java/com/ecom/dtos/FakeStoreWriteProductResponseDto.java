package com.ecom.dtos;

import com.ecom.models.Category;
import com.ecom.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreWriteProductResponseDto {
	private long id;
	private String title;
	private double price;
	private String description;
	private Category category;
	
	public Product toProduct() {
		Product product = new Product();
		product.setId(this.getId());
		product.setTitle(this.getTitle());
		product.setDescription(this.getDescription());
		product.setPrice(this.getPrice());
		
		Category category = new Category();
		category.setName(this.getCategory().getName());		
		product.setCategory(category);
		
		return product;
	}
}
