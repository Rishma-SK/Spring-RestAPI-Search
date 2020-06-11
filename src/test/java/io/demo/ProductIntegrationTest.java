package io.demo;


import static org.assertj.core.api.Assertions.assertThat;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import io.demo.model.Product;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public class ProductIntegrationTest {
	

    @LocalServerPort
    private int port;
	
	/*
	 * @SpringBootTest registers a TestRestTeplate bean so we can directly @Autowire
	 */
	@Autowired
	private TestRestTemplate restTemplate;

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void get_allProducts_ReturnsAllProducts_OK() throws URISyntaxException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Map<String,List<Product>>> responseEntity = this.restTemplate.exchange(createURLWithPort("/product"),
				HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String,List<Product>>>() {
				});
		
		Map<String,List<Product>> productResponse = responseEntity.getBody();

		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(productResponse.get("data").size() == 100);
	}
	
	@Test
	public void get_allProducts_ReturnsAllProducts_NOTFOUND() throws URISyntaxException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<Map<String,List<Product>>> responseEntity = this.restTemplate.exchange(createURLWithPort("/product?type=mobile"),
				HttpMethod.GET, entity, new ParameterizedTypeReference<Map<String,List<Product>>>() {
				});
		
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}
	
	 private String createURLWithPort(String uri) {
	        return "http://localhost:" + port + uri;
	    }



}
