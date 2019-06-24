package com.generics;

import java.util.List;

public class Product {
	
	Product(String name, float price, List<String> types) {
		this.name= name;
		this.price= price;
		this.types= types;
	}
	
	String name;
	float price;
	List<String> types;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	
	
}
