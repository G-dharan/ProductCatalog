package com.ecom.dtos;

import com.ecom.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadProductResponseDto {
	private long id;
	private String title;
	private double price;
	private String description;
	private String categoryName;
	private String image;
	
	public static ReadProductResponseDto from(Product product) {
		ReadProductResponseDto readProductResponseDto = new ReadProductResponseDto();
		readProductResponseDto.setId(product.getId());
		readProductResponseDto.setTitle(product.getTitle());
		readProductResponseDto.setDescription(product.getDescription());
		readProductResponseDto.setPrice(product.getPrice());
		if(product.getCategory() != null) {
			readProductResponseDto.setCategoryName(product.getCategory().getName());
		}
		readProductResponseDto.setImage(product.getImageUrl());
		return readProductResponseDto;
	}
}
