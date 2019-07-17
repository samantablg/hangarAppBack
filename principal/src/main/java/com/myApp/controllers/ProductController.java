package com.myApp.controllers;

import com.myApp.product.model.Product;
import com.myApp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public List<Product> getAllActiveProducts() { return productService.getAllActiveProducts();	}

	@GetMapping("/allProducts")
	public List<Product> getAllProducts() { return productService.getAllProducts();	}

	@GetMapping("/getProduct/{id}")
	public Product getProductById(@PathVariable Long id) { return productService.getProduct(id); }

	@PostMapping("/product")
	public Product createProduct(@Valid @RequestBody Product productReq) {
		Product p = new Product();
		p.setName(productReq.getName());
		p.setDescription(productReq.getDescription());
		return productService.create(p);
	}

	/*@PostMapping("/product")
	public Product createProduct(@Valid @RequestBody ProductRequest productRequest) {
		Product product = new Product();
		product.setName(productRequest.getName());
		product.setDescription(productRequest.getDescription());
		return productService.createProduct(product, productRequest.getPrice());
	}*/

	/*@PostMapping("/product/{id}/price")
	public Product UpdatePrice(@PathVariable Long id, @RequestBody float price) {
		Product product = productService.getProduct(id);
		return productService.createEntryPrice(product, price);
	}*/
	
	/* Este método ya no se usa, se utiliza el estado activo o inactivo*/
	@DeleteMapping("/product/{id}")
	public Product deleteProduct(@PathVariable Long id) {
		return productService.deleteProduct(id);
	}

	/*
	@GetMapping("/products/search/{letter}")
	public Product filterProducts(@PathVariable char letter) {
	    Product filterProduct = productService.filterName(letter);
		return new Product(filterProduct.getName(), filterProduct.getHangar());
	}*/

	@PutMapping("/product/{id}")
	public Product updateState(@PathVariable Long id) {	return productService.updateState(id); }

}
