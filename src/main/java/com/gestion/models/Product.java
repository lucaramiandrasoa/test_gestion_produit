package com.gestion.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
	@Id
	private String id;
	
	private java.lang.String name;
	private java.lang.Double price;
	private java.lang.String description;
	
	public Product() {
		
	}
	
	public Product(String name, Double price, String description) {
		this.name=name;
		this.price=price;
		this.description=description;
	}
	
//	GETTERS
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Double getPrice() {
		return price;
	}
	public String getDescription() {
		return description;
	}
//	SETTERS
	public void setName(String name) {
		this.name=name;
	}
	public void setPrice(Double price) {
		this.price=price;
	}
	public void  setDescription(String description) {
		this.description=description;
	}
	
	@Override
	  public String toString() {
	    return "Product [id=" + id + ", name=" + this.name + ", price=" + this.price + ", description=" + this.description + "]";
	  }
	
}
