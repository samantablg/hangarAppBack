package com.myApp.product.service;

import com.myApp.product.model.Product;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	List<Product> getAllActiveProducts();

    List<Product> getAllProductsWithName(String name);

	Product getProduct(long id);

	Product create(Product product);

	void deleteProduct(long id);

	//Product filterName(char letter);

    // ProductExtendedDto getProductWithCurrentPrice();

	Product updateState(long id);

	boolean existProduct(long id);

    boolean existProductByName(String name);

    Product modifyProduct(Product update);

    String getNameOfProductById(long id);

}
