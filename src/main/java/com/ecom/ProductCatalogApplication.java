package com.ecom;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.ecom.controllers.CategoryController;

@SpringBootApplication
//@EnableJpaAuditing
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)

public class ProductCatalogApplication{
	

	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println(categoryController.getAllCategories());
//		
//	}

}