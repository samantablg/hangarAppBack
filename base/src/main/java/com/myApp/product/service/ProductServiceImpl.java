package com.myApp.product.service;

import com.myApp.product.dao.ProductDao;
import com.myApp.product.exceptions.ProductException;
import com.myApp.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
    private ProductDao productDAO;

	@Override
	public List<Product> getAllProducts() {

		List<Product> products = productDAO.getAllProducts();
		if(!products.isEmpty())
			return products;
		throw new ProductException.NotFound();
	}

	@Override
	public List<Product> getAllActiveProducts() {

		List<Product> products = productDAO.getAllProducts();
		List<Product> result = new ArrayList<>();

		if(!products.isEmpty()) {
			for(Product p: products)
				if(p.isState())
					result.add(p);
			return result;
		}
		throw new ProductException.NotFound();
	}

    @Override
    public List<Product> getAllProductsWithName(String name) {

        List<Product> result = productDAO.findProductsByName(name);
        if (!result.isEmpty())
            return result;
        throw new ProductException.NotFound();
    }

	@Override
	public Product getProduct(long id) {
		if(productDAO.existProduct(id))
			return productDAO.getProduct(id);
		throw new ProductException.NotFound(id);
	}

    @Override
    public Product create(Product product) {
	    if(!productDAO.existProductByName(product.getName()))
	        return productDAO.createProduct(product);
	    throw new ProductException.ProductExistException();
    }

	public void deleteProduct(long id) {
		if (productDAO.existProduct(id))
			 productDAO.deleteProduct(id);
		throw new ProductException.NotFound(id);
	}

	@Override
	public Product updateState(long id) {
		if(productDAO.existProduct(id)) {
			Product product = productDAO.getProduct(id);
			product.setState(!product.isState());
			return productDAO.updateProduct(product);
		}
		throw new ProductException.NotFound(id);
	}

    @Override
    public Product modifyProduct(Product update) {
	    Product product = productDAO.getProduct(update.getId());
	    product.setName(update.getName());
	    product.setDescription(update.getDescription());
        return productDAO.editProduct(product);
    }


	@Override
	public boolean existProduct(long id) {
		return productDAO.existProduct(id);
	}

	/*Ejercicio java 8
	TODO actualizar al nuevo modelo producto -> sin hangar asignado

	private List<Product> searchByFirstLetter(char letter) {

		List<Product> products = productDAO.getAllProducts();
		return products.stream()
				.filter((product) -> letter == product.getName().charAt(0))
				.collect(Collectors.toList());
	}

	private List<Product> convertToUpperCase(List<Product> products) {

		return products.stream()
				.map(p -> new Product(p.getName().toUpperCase(), p.getHangar()))
				.collect(Collectors.toList());
	}

	private Product filterProductByLength(List<Product> products) {
		return products.stream().max(Comparator.comparing(Product::getName)).get();
	}

	public Product filterName(char letter) {
		List<Product> listLetter = searchByFirstLetter(letter);

		List<Product> listUpper = convertToUpperCase(listLetter);
		return filterProductByLength(listUpper);
	}
	*/

    @Override
    public String getNameOfProductById(long id) {
        return productDAO.getNameOfProductById(id);
    }

    @Override
    public boolean existProductByName(String name) {
        return productDAO.existProductByName(name);
    }
}
