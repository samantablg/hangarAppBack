package com.myApp.product.dao;

import java.util.List;

import com.myApp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductDao {
	
	List<Product> getAllProducts();

    List<Product> getProductsByName(String name);

    List<Product> getProductsActive();

	Page<Product> findByStateTrue(Pageable pageable);

	Product getProduct(Long id);
	
	Product createProduct(Product product);

	Product editProduct(Product product);

	Product updateProduct(Product product);

	void deleteProduct(long id);

	boolean isProductById(long id);

	boolean isProductByName(String name);

	boolean isProductByNameAndDescription(String name, String description);

	String getNameOfProductById(long id);

}
