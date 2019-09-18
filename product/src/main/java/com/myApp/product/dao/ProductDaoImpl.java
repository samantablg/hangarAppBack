package com.myApp.product.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<Product> findByStateTrue(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public Product getProduct(Long id) { return productRepository.getOne(id); }
	
	@Override
	public Product createProduct(Product product) { return productRepository.save(product);	}

    @Override
    public Product editProduct(Product product) { return productRepository.save(product);  }

    public void deleteProduct(long id) { productRepository.deleteById(id);}


	@Override
	public Product updateProduct(Product product) {
		return productRepository.saveAndFlush(product);
	}

	@Override
	public boolean isProductById(long id) {
		return productRepository.existsById(id);
	}

	@Override
	public boolean isProductByNameAndDescription(String name, String description) {
		return productRepository.existsByNameAndDescription(name, description); }

    @Override
    public boolean isProductByName(String name) {
        return productRepository.existsByName(name);
    }

	@Override
	public String getNameOfProductById(long id) {
		return productRepository.getNameOfProduct(id);
	}

}
