package io.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import io.demo.model.Product;

/*
 *  Service Layer
 */
@Service
public interface ProductService {
	
	/**
	 * To save list of products
	 * @param productList
	 */
	public void saveAllProducts(List<Product> productList);
	
	/**
	 * To fetch all products
	 * @return
	 */
	public List<Product> getAllProducts();
	
	/**
	 * To fetch products for the provided query params
	 * @param allParams
	 * @return
	 */
	public List<Product> getFilteredProducts(Map<String, String> allParams);
}
