package com.myApp.product.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myApp.product.repository.ProductRepository;
import com.myApp.model.Product;

@Component
public class ProductDaoImpl implements ProductDao {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAllProducts() { return productRepository.findAll();	}

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameWithTrueState(name);
    }

	@Override
	public List<Product> getProductsActive() {
		return productRepository.findAllWithTrueState();
	}

	@Override
	public Product getProduct(Long id) { return productRepository.getOne(id); }
	
	@Override
	public Product createProduct(Product product) { return productRepository.save(product);	}

    @Override
    public Product editProduct(Product product) { return productRepository.save(product);  }

    public void deleteProduct(long id) { productRepository.deleteById(id);}

	@Override
	public boolean existProduct(long id) {
		return productRepository.existsById(id);
	}

	@Override
	public boolean existProductByNameAndDescription(String name, String description) { return productRepository.existsProductByNameAndDescription(name, description); }

	@Override
	public Product updateProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

    @Override
    public boolean existProductByName(String name) {
        return (productRepository.existsProductByName(name));
    }

	@Override
	public String getNameOfProductById(long id) {
		return productRepository.getNameOfProduct(id);
	}

}
