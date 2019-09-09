package com.myApp.product.dao;

import java.util.List;

import com.myApp.model.Product;

public interface ProductDao {
	
	List<Product> getAllProducts();
	
	Product getProduct(Long id);

    List<Product> findProductsByName(String name);
	
	Product createProduct(Product product);

	Product editProduct(Product product);
	
	Boolean deleteProduct(long id);

	boolean existProduct(long id);

	Product updateProduct(Product product);

	boolean existProductByName(String name);

	String getNameOfProductById(long id);

}
