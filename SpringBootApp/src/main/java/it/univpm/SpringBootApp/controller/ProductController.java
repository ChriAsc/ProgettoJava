package it.univpm.SpringBootApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.SpringBootApp.model.Product;
import it.univpm.SpringBootApp.service.ProductService;


@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "/products")
	public ResponseEntity<Object> getProduct() {
		return new ResponseEntity<>(productService.getProduct(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
		productService.updateProduct(id, product);
		return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProduct(@PathVariable("id") Integer id) {
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product) {
		productService.createProduct(product);
		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}
}
