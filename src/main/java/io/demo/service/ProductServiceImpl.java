package io.demo.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import io.demo.model.Product;
import io.demo.repository.ProductRepository;
import io.demo.specs.ProductSpecification;
import io.demo.specs.SearchCriteria;
import io.demo.specs.SearchOperation;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
    private ProductRepository productRepository;
	
	
	public void saveAllProducts(List<Product> productList) {
		  productRepository.saveAll(productList);
	}

	public List<Product> getAllProducts() {
		return  (List<Product>) productRepository.findAll();
	}
	
	public List<Product> getFilteredProducts(Map<String, String> allParams){
		
		 ProductSpecification specType = new ProductSpecification();
		 ProductSpecification specCity = new ProductSpecification();
		 ProductSpecification specProperty = new ProductSpecification();
		 ProductSpecification psColor = new ProductSpecification();
		 ProductSpecification psPriceRange = new ProductSpecification();
		 ProductSpecification psGBLimitRange = new ProductSpecification();
     
	
		 allParams.entrySet().stream()
	      .forEach(e -> {
	    	  
	    	  if(e.getKey().equals("type")) {
	    		 
	    		  specType.add(new SearchCriteria("type", e.getValue(), SearchOperation.EQUAL));
	    	  }
	    	  if(e.getKey().equals("city")) {
	    		 
	    		  specCity.add(new SearchCriteria("store_address",e.getValue(), SearchOperation.MATCH));
	    	  }
	    	  if(e.getKey().equals("property")) {
	    		 
	    		  specProperty.add(new SearchCriteria("properties", e.getValue(), SearchOperation.MATCH));
	    	  }
	    	  if(e.getKey().equals("property:color")) {
	    		  
	              psColor.add(new SearchCriteria("properties", e.getValue(), SearchOperation.MATCH));
	    	  }
              if(e.getKey().endsWith("_price")) {
	    		  
            	  String min_price = allParams.get("min_price");
            	  String max_price = allParams.get("max_price");
            	  
            	  if(Objects.nonNull(min_price)) {
            		  psPriceRange.add(new SearchCriteria("price", min_price, SearchOperation.GREATER_THAN_EQUAL));
            	  }
            	  if(Objects.nonNull(max_price)) {
            		  psPriceRange.add(new SearchCriteria("price", max_price, SearchOperation.LESS_THAN_EQUAL));
            	  }
	              
	    	  }
	    	  
	    	  if(e.getKey().startsWith("property:gb_limit_")) {
	    		  
	    		     String gb_limit_min = allParams.get("property:gb_limit_min");
	    			 String gb_limit_max = allParams.get("property:gb_limit_max");
	    			 
	    			 
	    			 if(Objects.nonNull(gb_limit_max)) {
	            		  psGBLimitRange.add(new SearchCriteria("properties", "gb_limit:"+gb_limit_max, SearchOperation.LESS_THAN_EQUAL));
	            	  }
	    			  if(Objects.nonNull(gb_limit_min)) {
	    				  psGBLimitRange.add(new SearchCriteria("properties", "gb_limit:"+gb_limit_min, SearchOperation.GREATER_THAN_EQUAL));
	            	  }
	    		
	    	  }
	      });
		
		List<Product> filteredProducts = productRepository.findAll(Specification.where(specType).and(specCity).and(specProperty).and(psColor).and(psPriceRange).and(psGBLimitRange));
		return filteredProducts;
		
	}


}
