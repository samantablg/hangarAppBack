package com.myApp.product.dao;

import java.util.List;

import com.myApp.product.model.Product;

public interface ProductDao {
	
	List<Product> getAllProducts();
	
	Product getProduct(Long id);

    List<Product> findProductsByName(String name);
	
	Product createProduct(Product product);

	Product editProduct(Product product);
	
	Product deleteProduct(long id);

	boolean existProduct(long id);

	Product updateProduct(Product product);

	boolean existProductByName(Product product);

}
