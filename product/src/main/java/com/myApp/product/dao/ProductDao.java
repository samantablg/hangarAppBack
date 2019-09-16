package com.myApp.product.dao;

import java.util.List;

import com.myApp.model.Product;

public interface ProductDao {
	
	List<Product> getAllProducts();

    List<Product> getProductsByName(String name);

    List<Product> getProductsActive();

	Product getProduct(Long id);
	
	Product createProduct(Product product);

	Product editProduct(Product product);

	Product updateProduct(Product product);

	void deleteProduct(long id);

	boolean existProduct(long id);

	boolean existProductByName(String name);

	boolean existProductByNameAndDescription(String name, String description);

	String getNameOfProductById(long id);

}
