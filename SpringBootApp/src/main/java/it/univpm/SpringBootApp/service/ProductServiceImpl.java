package it.univpm.SpringBootApp.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import it.univpm.SpringBootApp.model.Product;
import it.univpm.SpringBootApp.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	private static Map<Integer, Product> productRepo = new HashMap<>();
	
	private final AtomicLong counter = new AtomicLong();
	
	public ProductServiceImpl() {
		Product honey = new Product();
		honey.setId((int)counter.incrementAndGet());
		honey.setName("Honey");
		productRepo.put(honey.getId(), honey);
		
		Product almond = new Product();
		almond.setId((int)counter.incrementAndGet());
		almond.setName("Almond");
		productRepo.put(almond.getId(), almond);
	}


	@Override
	public void createProduct(Product product) {
		if(productRepo.containsKey(product.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Existing id ...");
		}
		productRepo.put(product.getId(), product);
	}

	@Override
	public void updateProduct(Integer id, Product product) {
		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
	}

	@Override
	public void deleteProduct(Integer id) {
		productRepo.remove(id);
	}

	@Override
	public Collection<Product> getProduct() {
		return productRepo.values();
	}
	}