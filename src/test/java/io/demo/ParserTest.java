package io.demo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.mockito.InjectMocks;

import io.demo.model.Product;
import io.demo.util.CSVParser;

public class ParserTest {

	@InjectMocks
	private CSVParser parser;
	
	@Test
	public void test_loadAllProducts() throws IOException {
		
		List<Product> list = CSVParser.loadProducts("data.csv");
		assertEquals(list.size(),100);
	}
	
	@Test
	public void test_loadAllProducts_FileNotFound() throws IOException {
		
		List<Product> list = CSVParser.loadProducts("anotherData.csv");
		assertEquals(list.size(),0);
	}
}
