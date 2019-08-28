package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.product.builder.DtoBuilder;
import com.myApp.product.builder.ProductBuilder;
import com.myApp.product.dto.ProductDto;
import com.myApp.product.model.Product;
import com.myApp.product.repository.ProductRepository;
import com.myApp.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

	@Autowired
	ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllActiveProducts() {
	    List<Product> products = productService.getAllActiveProducts();

	    return new ResponseEntity<>(
	            products.stream().map(
	                    product -> new DtoBuilder(product).getProductDto()).collect(Collectors.toList()),
               HttpStatus.OK
        );
	}

	@GetMapping("/allProducts")
	public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();

        return new ResponseEntity<>(
                products.stream().map(
                        product -> new DtoBuilder(product).getProductDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    //TODO implementar a lo largo de todas las capas
    @GetMapping("/products/{page}/{items}")
    public ResponseEntity<Page<Product>> productList(@PathVariable("page") int page, @PathVariable("items") int items) {

        Pageable itemsToPage = PageRequest.of(page, items);
        Page<Product> allProducts = productRepository.findByStateTrue(itemsToPage);

        Page<Product> result = new PageImpl<>(
                new ArrayList<>(allProducts.getContent()),
                itemsToPage,
                allProducts.getTotalElements());

        return new ResponseEntity<>(
                result,
                HttpStatus.OK
        );
    }

	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable long id) {
		if(id<=0)
			throw new ControllerException.idNotAllowed(id);

		final Product product = productService.getProduct(id);
		return new ResponseEntity<>(
				new DtoBuilder(product).getProductDto(),
				HttpStatus.OK
		);
	}

	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDto productReq) {

	    if(productReq.getName()!= null && productReq.getDescription()!= null) {
	        Product product = new ProductBuilder(productReq).getProduct();
	        return new ResponseEntity<>(productService.create(product), HttpStatus.OK);
        }
        throw new ControllerException.productEmptyException();
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

	@PutMapping("/product")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto update) {
        if(update.getName()!= null && update.getDescription()!= null) {
            Product product = new ProductBuilder(update).getProduct();
            Product modify = productService.modifyProduct(product);
            return new ResponseEntity<> (new DtoBuilder(modify).getProductDto(), HttpStatus.OK);
        }
	    throw new ControllerException.productEmptyException();
    }

    //Logic Delete
    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateState(@PathVariable Long id) {
        if(id<=0)
            throw new ControllerException.idNotAllowed(id);
        return new ResponseEntity<>(productService.updateState(id), HttpStatus.OK);
    }

    @GetMapping("search/product")
    public ResponseEntity<List<ProductDto>> findProductByName(@RequestParam String p_name) {
        if (p_name.length()>0) {
            List<Product> result = productService.getAllProductsWithName(p_name);
            return new ResponseEntity<>(
                    result.stream().map(
                            product -> new DtoBuilder(product).getProductDto()).collect(Collectors.toList()),
                    HttpStatus.OK);
        }
        throw new ControllerException.searchProductException();
    }

}
