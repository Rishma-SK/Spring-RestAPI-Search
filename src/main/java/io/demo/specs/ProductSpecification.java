package io.demo.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import io.demo.model.Product;

public class ProductSpecification implements Specification<Product>{


	private static final long serialVersionUID = 1L;
	
	private List<SearchCriteria> list;

	public ProductSpecification() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		 //create a new predicate list
        List<Predicate> predicates = new ArrayList<>();
        
       
        //add add criteria to predicates
        for (SearchCriteria criteria : list) {
        	
        	
        	if (criteria.getOperation().equals(SearchOperation.GREATER_THAN_EQUAL)) {
        		
                predicates.add(builder.greaterThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } 
        	else if (criteria.getOperation().equals(SearchOperation.LESS_THAN_EQUAL)) {
        		
                predicates.add(builder.lessThanOrEqualTo(
                        root.get(criteria.getKey()), criteria.getValue().toString()));
            } else if (criteria.getOperation().equals(SearchOperation.EQUAL)) {
                predicates.add(builder.equal(
                        root.get(criteria.getKey()), criteria.getValue()));
            } 
            else if (criteria.getOperation().equals(SearchOperation.MATCH)) {
                predicates.add(builder.like(	
                        builder.lower(root.get(criteria.getKey())),
                        "%" + criteria.getValue().toString().toLowerCase() + "%"));
            } 
           
        }
        
        return builder.and(predicates.toArray(new Predicate[0]));
	}

	
}
