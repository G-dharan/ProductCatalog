package com.ecom.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.ProductRequestDto;
import com.ecom.dtos.ReadProductResponseDto;
import com.ecom.dtos.WriteProductResponseDto;
import com.ecom.exceptions.NoProductProvidedException;
import com.ecom.exceptions.NoProductsFoundException;
import com.ecom.exceptions.ProductNotCreatedException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.models.Product;
import com.ecom.services.FakeStoreProductService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {
	
	@NonNull
	private FakeStoreProductService productService;

	@GetMapping("/products/{id}")
	public ReadProductResponseDto getProduct(@PathVariable("id") long id) throws ProductNotFoundException {
		Product product = productService.getProduct(id);
		ReadProductResponseDto readProductResponseDto = ReadProductResponseDto.from(product);
		return readProductResponseDto;
	}
	
	@GetMapping("/products")
	public List<ReadProductResponseDto> getAllProduct() throws NoProductsFoundException {
		List<Product> products = productService.getAllProducts();
		List<ReadProductResponseDto> readProductResponseDtos = products.stream().map(ReadProductResponseDto::from).collect(Collectors.toList());
		return readProductResponseDtos;
	}
	
	@PostMapping("/products")
	public WriteProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) throws ProductNotCreatedException, NoProductProvidedException {
//		if(productRequestDto == null) {
//			throw new NoProductProvidedException("No product provided to create");
//		}
		Product product = productRequestDto.toProduct();
		product = productService.createProduct(product);
		WriteProductResponseDto writeProductResponseDto = WriteProductResponseDto.from(product);
		return writeProductResponseDto;
	}
	
	@PutMapping("/products/{id}")
	public WriteProductResponseDto putProduct(@PathVariable("id") long id, @RequestBody ProductRequestDto productRequestDto) {
		Product product = productRequestDto.toProduct();
		product = productService.putProduct(id, product);
		return WriteProductResponseDto.from(product);
	}

}