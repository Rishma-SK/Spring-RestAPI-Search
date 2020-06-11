package io.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.demo.model.Product;
import io.demo.service.ProductService;
/**
 * 
 * @author Rishma Sankarbabu
 *
 */

@RestController
public class ProductController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	@GetMapping(value="/product",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,List<Product>>> getFilteredProducts(@RequestParam Map<String,String> allParams){

		List<Product> productList = productService.getFilteredProducts(allParams);

		if(productList.size() == 0) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Map<String,List<Product>> result = new HashMap<>();
		result.put("data", productList);
		
		LOG.info("Filtered Resultset:::"+result);
		
		return new ResponseEntity<Map<String,List<Product>>>(result,HttpStatus.OK);

	}

}
