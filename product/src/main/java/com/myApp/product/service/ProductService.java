package com.myApp.product.service;

import com.myApp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

	List<Product> getAllProducts();

	List<Product> getAllActiveProducts();

    List<Product> getAllProductsWithNameLike(String name);

	Page<Product> findByStateTrue(Pageable pageable);

	Product getProduct(long id);

	Product create(Product product);

	void deleteProduct(long id);

	//Product filterName(char letter);

    // ProductExtendedDto getProductWithCurrentPrice();

	Product updateState(long id);

	boolean isProductById(long id);

    boolean isProductByName(String name);

    Product modifyProduct(Product update);

    String getNameOfProductById(long id);

}
