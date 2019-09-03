package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.product.builder.DtoBuilder;
import com.myApp.product.dto.ProductDto;
import com.myApp.product.model.Product;
import com.myApp.product_hangar.builder.Product_HangarBuilder;
import com.myApp.product_hangar.builder.Product_HangarDtoBuilder;
import com.myApp.product_hangar.dto.ProductName_HangarDto;
import com.myApp.product_hangar.dto.Product_HangarDto;
import com.myApp.product_hangar.model.Product_Hangar;
import com.myApp.product_hangar.service.Product_HangarServiceImpl;
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

    @PostMapping(value = "/productOfHangar", produces = "application/json; charset=utf-8")
    public ResponseEntity<Product_HangarDto> addProductToHangar(@RequestBody Product_HangarDto product_hangarDto) {
        Product_Hangar productOfHangar = new Product_HangarBuilder(product_hangarDto).getProduct_hangar();
        return new ResponseEntity<>(
                new Product_HangarDtoBuilder(product_hangarService.associateProductToHangar(productOfHangar)).getProduct_hangarDto(),
                HttpStatus.CREATED
        ) ;
    }

    @GetMapping("/productsOfHangar")
    public ResponseEntity<List<Product_HangarDto>> getRelationships() {
        List<Product_Hangar> productsOfHangars = product_hangarService.getAll();
        return new ResponseEntity<>(
                productsOfHangars.stream().map(
                        productOfHangar -> new Product_HangarDtoBuilder(productOfHangar).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/products/{idProduct}")
    public ResponseEntity<List<Product_HangarDto>> getHangarsOfProduct(@PathVariable long idProduct) {
        if(idProduct<=0) {
            throw new ControllerException.idNotAllowed(idProduct);
        }
        List<Product_Hangar> productsOfHangar = product_hangarService.getHangarsOfProduct(idProduct);
        return new ResponseEntity<>(
                productsOfHangar.stream().map(
                        productOfHangar -> new Product_HangarDtoBuilder(productOfHangar).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/products/hangar/{idHangar}")
    public ResponseEntity<List<Product_HangarDto>> getProductOfHangar(@PathVariable long idHangar) {
        if(idHangar<=0) {
            throw new ControllerException.idNotAllowed(idHangar);
        }
        List<Product_Hangar> productsOfHangar = product_hangarService.getProductsOfHangar(idHangar);
        return new ResponseEntity<>(
                productsOfHangar.stream().map(
                        productOfHangar -> new Product_HangarDtoBuilder(productOfHangar).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/link/productsOfHangar/{idHangar}")
    public ResponseEntity<List<ProductName_HangarDto>> getNameOfProductOfHangar(@PathVariable long idHangar) {
        if(idHangar<=0) {
            throw new ControllerException.idNotAllowed(idHangar);
        }
        product_hangarService.getNameOfProductsOfHangar(idHangar);
        return new ResponseEntity<>(product_hangarService.getNameOfProductsOfHangar(idHangar), HttpStatus.OK);
    }

    @PutMapping(value="/productOfHangar/update", produces = "application/json; charset=utf-8")
    public ResponseEntity<Product_HangarDto> updateAmount(@RequestBody Product_HangarDto update) {
        Product_Hangar productOfHangar = new Product_HangarBuilder(update).getProduct_hangar();
        Product_Hangar productOfHangarUpdated = product_hangarService.updateAmount(productOfHangar.getProduct(), productOfHangar.getHangar(), productOfHangar.getAmount());
        return new ResponseEntity<>(
                new Product_HangarDtoBuilder(productOfHangarUpdated).getProduct_hangarDto(),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value="/productOfHangar/delete", produces = "application/json; charset=utf-8")
    public ResponseEntity<Boolean> unlinkProductOfHangar(@RequestBody Product_HangarDto delete) {
        Product_Hangar productOfHangar = new Product_HangarBuilder(delete).getProduct_hangar();
        return new ResponseEntity<>(
                product_hangarService.unlinkProductOfHangar(productOfHangar.getProduct(), productOfHangar.getHangar()),
                HttpStatus.OK
        );
    }

    @GetMapping(value="productOfHangar/link/{idProduct}")
    public ResponseEntity<Boolean> isProductLinkToHangar(@PathVariable long idProduct) {
        if(idProduct<=0)
            throw new ControllerException.idNotAllowed(idProduct);
        return new ResponseEntity<>(
                product_hangarService.isProductLinkToHangar(idProduct), HttpStatus.OK
        );
    }

    @GetMapping("/products/unlink/{idHangar}")
    public ResponseEntity<List<ProductDto>> getProductsUnlinkToHangar(@PathVariable long idHangar) {
        List<Product> productsOfHangarById = product_hangarService.getProductsUnlinkOfHangar(idHangar);
        return new ResponseEntity<>(
                productsOfHangarById.stream().map(
                    product -> new DtoBuilder(product).getProductDto()).collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
