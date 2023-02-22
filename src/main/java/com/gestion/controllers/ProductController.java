package com.gestion.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.models.Product;
import com.gestion.repositories.I_ProductRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	I_ProductRepository productRepository;
	
//	Create_product
	@PostMapping("/product/create")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		try {
			Product _product = productRepository.save(new Product(product.getName(), product.getPrice(), product.getDescription()));
			return new ResponseEntity<>(_product, HttpStatus.CREATED);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	getAllProduct
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required=false) String name){
		try {
			List<Product> products = new ArrayList<Product>();
			
			if(name==null) 
				productRepository.findAll().forEach(products::add);
			
			else
				productRepository.findByNameContaining(name).forEach(products::add);
			
			if(products.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(products, HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/products/{id}")
	  public ResponseEntity<Product> getTutorialById(@PathVariable("id") String id) {
	    Optional<Product> productData = productRepository.findById(id);

	    if (productData.isPresent()) {
	      return new ResponseEntity<>(productData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PutMapping("/products/{id}")
	  public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
	    Optional<Product> productData = productRepository.findById(id);

	    if (productData.isPresent()) {
	    	Product _product = productData.get();
//	    	_product.setTitle(product.getTitle());
	    	_product.setName(product.getName());
	    	_product.setPrice(product.getPrice());
	    	_product.setDescription(product.getDescription());
	      return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
	
	@DeleteMapping("/products/{id}")
	  public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") String id) {
	    try {
	    	productRepository.deleteById(id);
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	@DeleteMapping("/products")
	  public ResponseEntity<HttpStatus> deleteAllproducts() {
	    try {
	    	productRepository.deleteAll();
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
	
}
