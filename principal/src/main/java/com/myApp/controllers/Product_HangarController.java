package com.myApp.controllers;

import com.myApp.product_hangar.model.Product_Hangar;
import com.myApp.product_hangar.service.Product_HangarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Product_HangarController {


    @Autowired
    Product_HangarServiceImpl product_hangarService;

    @PostMapping(value = "/productOfHangar", produces = "application/json; charset=utf-8")
    public Product_Hangar addProductToHangar(@RequestBody ProductOfHangar product_hangar) {
        Product_Hangar p_h = new Product_Hangar();
        p_h.setAmount(product_hangar.getAmount());
        p_h.setProduct(product_hangar.getProduct());
        p_h.setHangar(product_hangar.getHangar());
        return product_hangarService.associateProductToHangar(p_h);
    }

    @GetMapping("/productsOfHangar")
    public List<Product_Hangar> getProducts() {
        List<Product_Hangar> result = product_hangarService.getAll();
        return result;
    }

    @GetMapping("/products/hangar/{idHangar}")
    public List<Product_Hangar> getProductOfHangar(@PathVariable Long idHangar) {
        return product_hangarService.getProductsOfHangar(idHangar);
    }

    @GetMapping("/products/{idProduct}")
    public List<Product_Hangar> getHangarsOfProduct(@PathVariable Long idProduct) {
        return product_hangarService.getHangarsOfProduct(idProduct);
    }

    @PutMapping(value="/productOfHangar/update", produces = "application/json; charset=utf-8")
    public Product_Hangar updateAmount(@RequestBody ProductOfHangar update) {
        return product_hangarService.updateAmount(update.getProduct(), update.getHangar(), update.getAmount());
    }

    @PutMapping(value="/productOfHangar/delete", produces = "application/json; charset=utf-8")
    public Product_Hangar unlinkProductOfHangar(@RequestBody ProductOfHangar delete) {
        return product_hangarService.unlinkProductOfHangar(delete.getProduct(), delete.getHangar());
    }

}
