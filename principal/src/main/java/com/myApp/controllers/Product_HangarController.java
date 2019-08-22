package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.product_hangar.builder.Product_HangarBuilder;
import com.myApp.product_hangar.builder.Product_HangarDtoBuilder;
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
    Product_HangarServiceImpl product_hangarService;

    @PostMapping(value = "/productOfHangar", produces = "application/json; charset=utf-8")
    public ResponseEntity<Product_HangarDto> addProductToHangar(@RequestBody Product_HangarDto product_hangarDto) {
        Product_Hangar p_h = new Product_HangarBuilder(product_hangarDto).getProduct_hangar();
        Product_Hangar newp_h = product_hangarService.associateProductToHangar(p_h);
        return new ResponseEntity<>(
                new Product_HangarDtoBuilder(newp_h).getProduct_hangarDto(),
                HttpStatus.OK
        ) ;
    }

    @GetMapping("/productsOfHangar")
    public ResponseEntity<List<Product_HangarDto>> getProducts() {
        List<Product_Hangar> result = product_hangarService.getAll();
        return new ResponseEntity<>(
                result.stream().map(
                        p_h -> new Product_HangarDtoBuilder(p_h).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/products/{idProduct}")
    public ResponseEntity<List<Product_HangarDto>> getHangarsOfProduct(@PathVariable long idProduct) {
        if(idProduct<=0) {
            throw new ControllerException.idNotAllowed(idProduct);
        }
        List<Product_Hangar> result = product_hangarService.getHangarsOfProduct(idProduct);
        return new ResponseEntity<>(
                result.stream().map(
                        p_h -> new Product_HangarDtoBuilder(p_h).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("/products/hangar/{idHangar}")
    public ResponseEntity<List<Product_HangarDto>> getProductOfHangar(@PathVariable long idHangar) {
        if(idHangar<=0) {
            throw new ControllerException.idNotAllowed(idHangar);
        }
        List<Product_Hangar> result = product_hangarService.getProductsOfHangar(idHangar);
        return new ResponseEntity<>(
                result.stream().map(
                        p_h -> new Product_HangarDtoBuilder(p_h).getProduct_hangarDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @PutMapping(value="/productOfHangar/update", produces = "application/json; charset=utf-8")
    public ResponseEntity<Product_HangarDto> updateAmount(@RequestBody Product_HangarDto update) {
        Product_Hangar p_h = new Product_HangarBuilder(update).getProduct_hangar();
        Product_Hangar updatep_h = product_hangarService.updateAmount(p_h.getProduct(), p_h.getHangar(), p_h.getAmount());
        return new ResponseEntity<>(
                new Product_HangarDtoBuilder(updatep_h).getProduct_hangarDto(),
                HttpStatus.OK
        );
    }

    @PutMapping(value="/productOfHangar/delete", produces = "application/json; charset=utf-8")
    public ResponseEntity<Product_HangarDto> unlinkProductOfHangar(@RequestBody Product_HangarDto delete) {
        Product_Hangar p_h = new Product_HangarBuilder(delete).getProduct_hangar();
        Product_Hangar deletep_h = product_hangarService.unlinkProductOfHangar(p_h.getProduct(), p_h.getHangar());
        return new ResponseEntity<>(
                new Product_HangarDtoBuilder(deletep_h).getProduct_hangarDto(),
                HttpStatus.OK
        );
    }

}
