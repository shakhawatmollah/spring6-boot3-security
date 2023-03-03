package com.shakhawat.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.shakhawat.dto.Product;

import jakarta.annotation.PostConstruct;

@Service
public class ProductService {
	
	List<Product> productList = null;
	
	@PostConstruct
	public void loadProductFromDB() {
		productList = IntStream.range(1, 100)
				.mapToObj(i -> Product.builder()
						.productId(i)
						.name("Prod-"+i)
						.qty(new Random().nextInt(10))
						.price(new Random().nextInt(500)).build())
				.collect(Collectors.toList());
	}

	public List<Product> getProducts() {
		return productList;
	}

	public Product getProduct(int id) {
		return productList.stream()
				.filter(prod -> prod.getProductId()==id)
				.findAny()
				.orElseThrow(()->new RuntimeException("Product not found by id "+id));
	}

}
