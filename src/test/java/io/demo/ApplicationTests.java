package io.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.google.common.collect.Iterators;

import io.demo.controller.ProductController;
import io.demo.model.Product;
import io.demo.service.ProductService;

@SpringBootTest
class ApplicationTests {
	
	@Autowired
	private ProductController controller;	
	
	@MockBean
	private ProductService productService;
	
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
	
}
