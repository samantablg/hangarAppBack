package com.myApp.product.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myApp.product.repository.ProductRepository;
import com.myApp.product.model.Product;

@Component
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() {
		
		List<Product> products = productRepository.findAll();
		if(products != null)
			return products;
		return null;
	}

    @Override
    public List<Product> findProductsByName(String name) {
        return productRepository.findByNameWithTrueState(name);
    }

	@Override
	public Product getProduct(Long id) {
		return productRepository.getOne(id);
	}
	
	@Override
	public Product createProduct(Product product) {
		if(productRepository.findProductByName(product.getName()) == null) {
			return productRepository.save(product);
		}
		return null;
	}

    @Override
    public Product editProduct(Product product) {
	    if(productRepository.findProductByNameAndDescription(product.getName(), product.getDescription()) == null) {
	        return productRepository.save(product);
        }
        return null;
    }

    public Product deleteProduct(long id) {
		
		Product product = productRepository.getOne(id);
		if(product != null)
			productRepository.delete(product);
		return null;	
	}


	@Override
	public boolean existProduct(long id) {
		return productRepository.existsById(id);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Override
	public boolean existProductByName(Product product) {
		if(productRepository.findProductByName(product.getName()) != null)
			return true;
		return false;
	}
}
