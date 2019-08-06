package com.myApp.product.service;

import com.myApp.product.model.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	List<Product> getAllActiveProducts();

	Product getProduct(Long id);

	Product create(Product product);

	//Product createProduct(Product product, float price);

	Product deleteProduct(Long id);

	//Product filterName(char letter);

	Product updateState(Long id);

	//Product createEntryPrice(Product product, float price);

	boolean existProduct(Long id);

    Product modifyProduct(Product update);

}
