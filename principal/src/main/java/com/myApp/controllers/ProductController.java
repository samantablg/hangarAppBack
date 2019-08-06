package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.product.builder.ProductDtoBuilder;
import com.myApp.product.dto.ProductDto;
import com.myApp.product.model.Product;
import com.myApp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable long id) {
		if(id<=0)
			throw new ControllerException.idNotAllowed(id);

		final Product product = productService.getProduct(id);
		return new ResponseEntity<ProductDto>(
				new ProductDtoBuilder(product).getProductDto(),
				HttpStatus.OK
		);
	}

	@PostMapping("/product")
	public Product createProduct(@Valid @RequestBody Product productReq) {
		Product p = new Product();
		p.setName(productReq.getName());
		p.setDescription(productReq.getDescription());
		return productService.create(p);
	}

	//Este método ya no se usa, se utiliza el estado activo o inactivo
	/*@DeleteMapping("/product/{id}")
	public Product deleteProduct(@PathVariable Long id) {
		if(id<=0)
			throw new ControllerException.idNotAllowed(id);
		return productService.deleteProduct(id);
	}*/

	/*
	@GetMapping("/products/search/{letter}")
	public Product filterProducts(@PathVariable char letter) {
	    Product filterProduct = productService.filterName(letter);
		return new Product(filterProduct.getName(), filterProduct.getHangar());
	}*/

	//Logic Delete
	@PutMapping("/product/{id}")
	public Product updateState(@PathVariable Long id) {
		if(id<=0)
			throw new ControllerException.idNotAllowed(id);
		return productService.updateState(id);
	}

	@PutMapping("/product")
    public Product updateProduct(@RequestBody Product update) {
	    return productService.modifyProduct(update);
    }

}
