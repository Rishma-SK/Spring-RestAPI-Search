package io.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.demo.controller.ProductController;
import io.demo.model.Product;
import io.demo.service.ProductService;


@WebMvcTest(ProductController.class)
public class ProductControllerTest {
	
	@Autowired
	 MockMvc mvc;
	
	@MockBean
	 private ProductService productService;
	
	@Autowired
	ObjectMapper mapper;
	
	
	@Test
	public void test_getAllProducts() throws Exception{
		
		List<Product> productList = new ArrayList<>();
		Product product1 = new Product("phone", "color:silver", 400, "Olsson gatan, Karlskrona");
		Product product2 = new Product("subscription", "gb_limit:10", 794, "Johansson gatan, Stockholm");
		
		productList.add(product1);
		productList.add(product2);
		
		Map<String,String> params = new HashMap<>();
		
		
		// Mocking out the product service
				 Mockito.when(productService.getFilteredProducts(params)).thenReturn(productList);
				mvc.perform(MockMvcRequestBuilders.get("/product"))
						.andExpect(status().isOk());
				
	}
	
	@Test
	public void test_getProducts_WithType() throws Exception{
		
		List<Product> productList = new ArrayList<>();
		Product product1 = new Product("phone", "color:silver", 400, "Olsson gatan, Karlskrona");
		Product product2 = new Product("subscription", "gb_limit:10", 794, "Johansson gatan, Stockholm");
		
		productList.add(product1);
		productList.add(product2);
		
		Map<String,String> params = new HashMap<>();
		params.put("type", "phone");
		
		// Mocking out the product service
		        Product singleProduct = new Product("phone", "color:silver", 400, "Olsson gatan, Karlskrona");
		        List<Product> outputValue = Collections.singletonList(singleProduct);
		         
				Mockito.when(productService.getFilteredProducts(params)).thenReturn(outputValue);
				mvc.perform(MockMvcRequestBuilders.get("/product?type=phone"))
						.andExpect(status().isOk());
	}
	
	@Test
	public void test_getProducts_WithTypeAndCity() throws Exception{
		
		List<Product> productList = new ArrayList<>();
		Product product1 = new Product("phone", "color:silver", 400, "Olsson gatan, Karlskrona");
		Product product2 = new Product("subscription", "gb_limit:10", 794, "Johansson gatan, Stockholm");
		Product product3 = new Product("phone", "color:brun", 324, "Johansson gatan, Stockholm");
		Product product4 = new Product("subscription", "gb_limit:10", 655, "Johansson gatan, Stockholm");
		
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		productList.add(product4);
		
		Map<String,String> params = new HashMap<>();
		params.put("type", "phone");
		params.put("city", "Stockholm");
		
		// Mocking out the product service
		        Product singleProduct = new Product("phone", "color:brun", 324.0, "Johansson gatan, Stockholm");
		        List<Product> outputValue = Collections.singletonList(singleProduct);
		         
				Mockito.when(productService.getFilteredProducts(params)).thenReturn(outputValue);
				mvc.perform(MockMvcRequestBuilders.get("/product?type=phone&city=Stockholm"))
						.andExpect(status().isOk());
	}
	
	
	@Test
	public void test_getProducts_WithPriceRange() throws Exception{
		
		List<Product> productList = new ArrayList<>();
		Product product1 = new Product("phone", "color:gul", 172, "Winona gatan, Malmö");
		Product product2 = new Product("subscription", "gb_limit:50", 160, "Karlsson gränden, Karlskrona");
		Product product3 = new Product("phone", "color:röd", 145, "Eriksson vägen, Malmö");
		Product product4 = new Product("subscription", "gb_limit:10", 130, "Gustafsson gärdet, Karlskrona");
		
		
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		productList.add(product4);
		
		Map<String,String> params = new HashMap<>();
		params.put("min_price", "100");
		params.put("max_price", "150");
		
		// Mocking out the product service
		     
		List<Product> resultList =  new ArrayList<>();     
		resultList.add(product3);
		resultList.add(product4);
		
		Mockito.when(productService.getFilteredProducts(params)).thenReturn(resultList);
		mvc.perform(MockMvcRequestBuilders.get("/product?min_price=100&max_price=150"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void test_getProducts_NOTFOUND() throws Exception {

		Map<String,String> params = new HashMap<>();
		params.put("type", "phone");
		params.put("properties", "gb_limit:10");
		
		List<Product> productList = new ArrayList<>();
		Mockito.when(productService.getFilteredProducts(params)).thenReturn(productList);
         mvc.perform(
				MockMvcRequestBuilders.get("/product?type=phone&properties=gb_limit:10"))
				.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void test_getUnknownType_NOTFOUND() throws Exception {

		Map<String,String> params = new HashMap<>();
		params.put("type", "mobile");

		List<Product> productList = new ArrayList<>();
		Mockito.when(productService.getFilteredProducts(params)).thenReturn(productList);
		  mvc.perform(
					MockMvcRequestBuilders.get("/product?type=mobile"))
					.andExpect(status().isNotFound());
	}
	
	
	
}
