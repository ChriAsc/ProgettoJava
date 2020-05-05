package it.univpm.SpringBootApp.service;

import java.util.Collection;
import it.univpm.SpringBootApp.model.Product;

public interface ProductService {
	public abstract void createProduct(Product product);
	public abstract void updateProduct(Integer id, Product product);
	public abstract void deleteProduct(Integer id);
	public abstract Collection<Product> getProduct();
}