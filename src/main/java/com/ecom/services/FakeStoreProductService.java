package com.ecom.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecom.dtos.FakeStoreReadProductResponseDto;
import com.ecom.dtos.FakeStoreWriteProductResponseDto;
import com.ecom.exceptions.NoProductsFoundException;
import com.ecom.exceptions.ProductNotCreatedException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.models.Product;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class FakeStoreProductService implements ProductService {
	
	@NonNull
	RestTemplate restTemplate;

	public Product getProduct(long id) throws ProductNotFoundException {
		FakeStoreReadProductResponseDto FakeStoreReadProductResponseDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, 
				FakeStoreReadProductResponseDto.class);
		if(FakeStoreReadProductResponseDto == null) {
			throw new ProductNotFoundException("Product of id "+id+" not found");
		}

		return FakeStoreReadProductResponseDto.toProduct();
	}

	public List<Product> getAllProducts() throws NoProductsFoundException {
		FakeStoreReadProductResponseDto[] FakeStoreReadProductResponseDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreReadProductResponseDto[].class);
		if(FakeStoreReadProductResponseDtos == null) {
			throw new NoProductsFoundException("There are no products");
		}
		List<Product> products = new ArrayList();
		for(FakeStoreReadProductResponseDto FakeStoreReadProductResponseDto: FakeStoreReadProductResponseDtos) {
			Product product = FakeStoreReadProductResponseDto.toProduct();
			products.add(product);
		}
		return products;
	}

	public Product createProduct(Product product) throws ProductNotCreatedException {
		String url = "https://fakestoreapi.com/products";
		FakeStoreWriteProductResponseDto FakeStoreWriteProductResponseDto = restTemplate.postForObject(url, product, FakeStoreWriteProductResponseDto.class);
		if(FakeStoreWriteProductResponseDto == null) {
			throw new ProductNotCreatedException("Requested product not created");
		}
		product = FakeStoreWriteProductResponseDto.toProduct();
		System.out.println(product.getImageUrl());
		return product;
	}

	public Product putProduct(long id, Product product) {
		String url = "https://fakestoreapi.com/products/" + id;
		ResponseEntity<FakeStoreWriteProductResponseDto> updateproductResponseEntity = restTemplate.exchange(url, HttpMethod.PUT,
				new HttpEntity<Product>(product), FakeStoreWriteProductResponseDto.class);
		HttpStatusCode httpStatusCode = updateproductResponseEntity.getStatusCode();
		return updateproductResponseEntity.getBody().toProduct();
	}

}
