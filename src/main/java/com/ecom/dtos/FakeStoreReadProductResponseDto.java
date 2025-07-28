package com.ecom.dtos;

import com.ecom.models.Category;
import com.ecom.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreReadProductResponseDto {
	private long id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	
	public Product toProduct() {
		Product product = new Product();
		product.setId(this.getId());
		product.setTitle(this.getTitle());
		product.setDescription(this.getDescription());
		product.setPrice(this.getPrice());
		
		Category category = new Category();
		category.setName(this.getCategory());		
		product.setCategory(category);
		
		product.setImageUrl(this.getImage());
		return product;
	}
}
