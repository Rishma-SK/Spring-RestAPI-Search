package io.demo.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.demo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long>, JpaSpecificationExecutor<Product>{

	
}
