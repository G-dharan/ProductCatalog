package com.ecom.services;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.ecom.exceptions.InvalidCategoryException;
import com.ecom.exceptions.NoProductsFoundException;
import com.ecom.exceptions.ProductNotCreatedException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.models.Product;

public interface ProductService {
	Product getProduct(long id) throws ProductNotFoundException;
	Page<Product> getAllProducts(int pageNumber, int pageSize) throws NoProductsFoundException;
	Product createProduct(String name, String description, double price, String category, String image)  throws ProductNotCreatedException;
	Product putProduct(long id, Product product)  throws InvalidCategoryException;
	Page<Product> getProductByName(String name, int pageNumber, int pageSize);
	List<Product> getProductsBySpec(Specification<Product> spec);
}
