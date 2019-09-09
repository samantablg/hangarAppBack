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
	public List<Product> getAllProducts() {
		
		List<Product> products = productRepository.findAll();
		if(!products.isEmpty())
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

    public Boolean deleteProduct(long id) {
		if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
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
    public String getNameOfProductById(long id) {
        return productRepository.getNameOfProduct(id);
    }

    @Override
    public boolean existProductByName(String name) {
        return (productRepository.findProductByName(name) != null);
    }
}
