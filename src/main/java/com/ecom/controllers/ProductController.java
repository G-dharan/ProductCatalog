package com.ecom.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.Specifications.ProductSpecifications;
import com.ecom.dtos.ProductRequestDto;
import com.ecom.dtos.ReadProductResponseDto;
import com.ecom.dtos.WriteProductResponseDto;
import com.ecom.exceptions.InvalidCategoryException;
import com.ecom.exceptions.NoProductProvidedException;
import com.ecom.exceptions.NoProductsFoundException;
import com.ecom.exceptions.ProductNotCreatedException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.models.Product;
import com.ecom.services.ProductService;

import lombok.NonNull;

@RestController
public class ProductController {
	
	@NonNull
	private ProductService productService;
	
	public ProductController(@Qualifier("ProductDBService") ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/products/{id}")
	public ReadProductResponseDto getProductById(@PathVariable("id") long id) throws ProductNotFoundException {
		Product product = productService.getProduct(id);
		ReadProductResponseDto readProductResponseDto = ReadProductResponseDto.from(product);
		return readProductResponseDto;
	}
	
	
	@GetMapping("/products/")
	public List<ReadProductResponseDto> getProducts(@RequestBody ProductRequestDto productRequestDto) throws ProductNotFoundException {
		Specification<Product> spec = Specification.where(ProductSpecifications.hasName(productRequestDto.getTitle()))
			    .and(ProductSpecifications.hasPrice(productRequestDto.getPrice()));

		List<Product> products = productService.getProductsBySpec(spec);
		return products.stream().map(product -> ReadProductResponseDto.from(product)).toList();
	}
	
	@GetMapping("/products")
	public Page<ReadProductResponseDto> getProductByName(@RequestParam("name") String name, @RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) throws ProductNotFoundException {
		System.out.println(name);
		Page<Product> products = productService.getProductByName(name, pageNumber, pageSize);
//		System.out.println(products);
		Page<ReadProductResponseDto> productResponseDtos = products.map(ReadProductResponseDto::from);
//		System.out.println(productResponseDtos);
//		List<ReadProductResponseDto> productResponseDtos =products.stream().map(ReadProductResponseDto::from).toList();
		return productResponseDtos;
	}
	
	@GetMapping("/products/{pageNumber}/{pageSize}")
	public Page<ReadProductResponseDto> getAllProducts(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize")  int pageSize)  throws NoProductsFoundException {
		Page<Product> products = productService.getAllProducts(pageNumber, pageSize);
		Page<ReadProductResponseDto> readProductResponseDtos = products.map(product -> ReadProductResponseDto.from(product));
		return readProductResponseDtos;
	}
	
//	@PostMapping("/products")
//	public WriteProductResponseDto createProduct(@RequestBody ProductRequestDto productRequestDto) throws ProductNotCreatedException, NoProductProvidedException {
////		if(productRequestDto == null) {
////			throw new NoProductProvidedException("No product provided to create");
////		}
//		Product product = productRequestDto.toProduct();
//		product = productService.createProduct(productRequestDto.getTitle(), productRequestDto.getDescription(), productRequestDto.getPrice(), productRequestDto.getCategory(), productRequestDto.getImage());
//		WriteProductResponseDto writeProductResponseDto = WriteProductResponseDto.from(product);
//		return writeProductResponseDto;
//	}
	
	@PutMapping("/products/{id}")
	public WriteProductResponseDto putProduct(@PathVariable("id") long id, @RequestBody ProductRequestDto productRequestDto)  throws InvalidCategoryException{
		Product product = productRequestDto.toProduct();
		product = productService.putProduct(id, product);
		return WriteProductResponseDto.from(product);
	}

}