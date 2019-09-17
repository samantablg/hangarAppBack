package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.product.builder.DtoBuilder;
import com.myApp.product.dto.ProductDto;
import com.myApp.model.Product;
import com.myApp.product_hangar.builder.Product_HangarBuilder;
import com.myApp.product_hangar.builder.Product_HangarDtoBuilder;
import com.myApp.product_hangar.dto.ProductName_HangarDto;
import com.myApp.product_hangar.dto.Product_HangarDto;
import com.myApp.product_hangar.model.Product_Hangar;
import com.myApp.product_hangar.service.Product_HangarServiceImpl;
import com.myApp.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Product_HangarController {

    @Autowired
    private Product_HangarServiceImpl product_hangarService;

    @Autowired
    private Util util;

    @PostMapping(value = "/productOfHangar")
    public ResponseEntity<Product_HangarDto> addProductToHangar(@RequestBody Product_HangarDto product_hangarDto) {
        Product_Hangar product_hangar = new Product_HangarBuilder(product_hangarDto).getProduct_hangar();
        return new ResponseEntity<>(
                new Product_HangarDtoBuilder(product_hangarService.associateProductToHangar(product_hangar)).getProduct_hangarDto(),
                HttpStatus.CREATED
        ) ;
    }

    @GetMapping("/productsOfHangar")
    public ResponseEntity<List<Product_HangarDto>> getRelationships() {
        final List<Product_Hangar> products_hangars = product_hangarService.getAll();
        return new ResponseEntity<>(
                products_hangars.stream().map(
                        product_hangar -> new Product_HangarDtoBuilder(product_hangar).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/products/{id_product}")
    public ResponseEntity<List<Product_HangarDto>> getHangarsOfProduct(@PathVariable long id_product) {
        util.checkId(id_product);
        final List<Product_Hangar> productsOfHangar = product_hangarService.getHangarsOfProduct(id_product);
        return new ResponseEntity<>(
                productsOfHangar.stream().map(
                        productOfHangar -> new Product_HangarDtoBuilder(productOfHangar).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/products/hangar/{id_hangar}")
    public ResponseEntity<List<Product_HangarDto>> getProductOfHangar(@PathVariable long id_hangar) {
        util.checkId(id_hangar);
        final List<Product_Hangar> productsOfHangar = product_hangarService.getProductsOfHangar(id_hangar);
        return new ResponseEntity<>(
                productsOfHangar.stream().map(
                        product_hangar -> new Product_HangarDtoBuilder(product_hangar).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/link/productsOfHangar/{id_hangar}")
    public ResponseEntity<List<ProductName_HangarDto>> getNameOfProductOfHangar(@PathVariable long id_hangar) {
        util.checkId(id_hangar);;
        product_hangarService.getNameOfProductsOfHangar(id_hangar);
        return new ResponseEntity<>(
                product_hangarService.getNameOfProductsOfHangar(id_hangar),
                HttpStatus.OK
        );
    }

    @PutMapping(value="/productOfHangar/update", produces = "application/json; charset=utf-8")
    public ResponseEntity<Product_HangarDto> updateAmount(@RequestBody Product_HangarDto product_hangarDto) {
        Product_Hangar product_hangar = new Product_HangarBuilder(product_hangarDto).getProduct_hangar();
        Product_Hangar _product_hangar = product_hangarService.updateAmount(product_hangar.getProduct(), product_hangar.getHangar(), product_hangar.getAmount());
        return new ResponseEntity<>(
                new Product_HangarDtoBuilder(_product_hangar).getProduct_hangarDto(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value="/productOfHangar/delete", produces = "application/json; charset=utf-8")
    public ResponseEntity<Boolean> unlinkProductOfHangar(@RequestBody Product_HangarDto product_hangarDto) {
        Product_Hangar product_hangar = new Product_HangarBuilder(product_hangarDto).getProduct_hangar();
        product_hangarService.unlinkProductOfHangar(product_hangar.getProduct(), product_hangar.getHangar());
        return new ResponseEntity<>(
                true,
                HttpStatus.OK
        );
    }

    @GetMapping(value="productOfHangar/link/{id_product}")
    public ResponseEntity<Boolean> isProductLinkToHangar(@PathVariable long id_product) {
        util.checkId(id_product);
        return new ResponseEntity<>(
                product_hangarService.isProductLinkToHangar(id_product), HttpStatus.OK
        );
    }

    @GetMapping("/products/unlink/{id_hangar}")
    public ResponseEntity<List<ProductDto>> getProductsUnlinkToHangar(@PathVariable long id_hangar) {
        util.checkId(id_hangar);
        final List<Product> productsOfHangarById = product_hangarService.getProductsUnlinkOfHangar(id_hangar);
        return new ResponseEntity<>(
                productsOfHangarById.stream().map(
                    product -> new DtoBuilder(product).getProductDto()).collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
