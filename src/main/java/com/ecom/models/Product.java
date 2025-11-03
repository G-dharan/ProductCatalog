package com.ecom.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity

public class Product extends BaseModel {


	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", description=" + description + ", category=" + category
				+ ", imageUrl=" + imageUrl + "]";
	}
	private String name;
	private double price;
	private String description;

	@ManyToOne
	private Category category;
	private String imageUrl;
}
