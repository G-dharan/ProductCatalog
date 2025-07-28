package com.ecom.dtos;

import com.ecom.models.Product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteProductResponseDto {
	private long id;
	private String title;
	private double price;
	private String description;
	private String categoryName;
	private String image;
	
	public static WriteProductResponseDto from(Product product) {
		WriteProductResponseDto writeProductResponseDto = new WriteProductResponseDto();
		writeProductResponseDto.setId(product.getId());
		writeProductResponseDto.setTitle(product.getTitle());
		writeProductResponseDto.setDescription(product.getDescription());
		writeProductResponseDto.setPrice(product.getPrice());
		if(product.getCategory() != null) {
			writeProductResponseDto.setCategoryName(product.getCategory().getName());
		}
		writeProductResponseDto.setImage(product.getImageUrl());
		return writeProductResponseDto;
	}
}