package io.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 
 * 
 * @author Rishma Sankarbabu
 *
 */
@Entity
public class Product implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String type; // phone or subscription
	private String properties; // gb_limit or color
	private double price;  // min_price or max_price
	private String store_address;

	// Default constructor
	public Product() {
		
	}
	
	public Product(String type, String properties, double d, String store_address) {
		this.type = type;
		this.properties = properties;
		this.price = d;
		this.store_address = store_address;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	
	public String getStore_address() {
		return store_address;
	}
	public void setStore_address(String store_address) {
		this.store_address = store_address;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [type=" + type + ", properties=" + properties + ", price=" + price + ", store_address="
				+ store_address + "]";
	}

	
}
