package com.myApp.product.service;

import com.myApp.hangar.service.HangarServiceImpl;
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
    ProductDao productDAO;

	@Autowired
	HangarServiceImpl hangarService;

	/*@Autowired
	PriceServiceImpl priceService;

	@Autowired
	Product_HangarServiceImpl product_hangarService;*/

	@Override
	public List<Product> getAllProducts() {

		List<Product> products = productDAO.getAllProducts();
		if(products != null)
			return products;
		throw new ProductException.NotFound();
	}

	@Override
	public List<Product> getAllActiveProducts() {

		List<Product> products = productDAO.getAllProducts();
		List<Product> result = new ArrayList<>();

		if(products != null) {
			for(Product p: products)
				if(p.isState())
					result.add(p);

			return result;
		}
		throw new ProductException.NotFound();
	}

	@Override
	public Product getProduct(Long id) {
		if(productDAO.existProduct(id)) {
			return productDAO.getProduct(id);
		}
		throw new ProductException.NotFound(id);
	}

    public Product create(Product product) {
	    if(!productDAO.existProductByName(product))
	        return productDAO.createProduct(product);
	    throw new ProductException.ProductExistException();
    }

	/*@Override
	public Product createProduct(Product product, float price) {
		if(!productDAO.existProductByName(product)) {
			try {
				productDAO.createProduct(product);
				priceService.createEntryPrice(product, price);
				return product;
			} catch (Exception e) {
				throw new ProductException.ProductExistException();
			}
		}
		throw new ProductException.ProductExistException();
	}*/

	public Product deleteProduct(Long id) {

		if (productDAO.existProduct(id))
			return productDAO.deleteProduct(id);
		throw new ProductException.NotFound(id);
	}

	@Override
	public Product updateState(Long id) {

		if(productDAO.existProduct(id)) {
			Product product = productDAO.getProduct(id);
			if(product.isState()) {
				product.setState(false);
			} else {
				product.setState(true);
			}

			Product aProduct = product;
			return productDAO.updateProduct(aProduct);

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

	/*@Override
	public Product createEntryPrice(Product product, float price) {
		priceService.createEntryPrice(product, price);
		return product;
	}*/

	@Override
	public boolean existProduct(Long id) {
		return productDAO.existProduct(id);
	}

	/*Ejercicio java 8
	TODO actualizar al nuevo modelo producto -> sin com.myHangar.hangar asignado

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
}
