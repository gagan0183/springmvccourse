package com.spring.service;

import java.util.List;

import com.spring.domain.Product;

public interface ProductService {
	List<Product> listAllProducts();

	Product getProductById(Integer id);

	Product saveProduct(Product product);

	void deleteProduct(Integer id);
}
