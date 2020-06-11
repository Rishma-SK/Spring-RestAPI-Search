package io.demo;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.demo.model.Product;
import io.demo.service.ProductService;
import io.demo.util.CSVParser;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application implements CommandLineRunner {

	@Autowired
	ProductService productService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2).select()
	         .apis(RequestHandlerSelectors.basePackage("io.demo.controller")).build();
	   }

	/**
	 * Load data from csv files
	 * @throws IOException 
	 */
	@Override
	public void run(String... args) throws Exception {

		List<Product> productList = CSVParser.loadProducts("data.csv");
	 	productService.saveAllProducts(productList);
	}

}
