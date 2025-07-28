package com.ecom.services;

import java.util.List;

import com.ecom.exceptions.NoProductsFoundException;
import com.ecom.exceptions.ProductNotCreatedException;
import com.ecom.exceptions.ProductNotFoundException;
import com.ecom.models.Product;

public interface ProductService {
	 Product getProduct(long id) throws ProductNotFoundException;
	 List<Product> getAllProducts() throws NoProductsFoundException;
	 Product createProduct(Product product) throws ProductNotCreatedException;
	 public Product putProduct(long id, Product product);
}
