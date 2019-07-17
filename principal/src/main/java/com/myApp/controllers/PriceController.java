package com.myApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.myApp.price.model.Price;
import com.myApp.price.service.PriceServiceImpl;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/price")
public class PriceController {

    @Autowired
    PriceServiceImpl priceService;

    @GetMapping("/prices")
    public List<Price> getPrices() {
        return priceService.getAllPrices();
    }

    @PutMapping("/product/{id}")
    public Price updatePrice(@PathVariable long id, @RequestBody float price) {
        return priceService.createEntryPriceToProduct(id, price);
    }

    @GetMapping("/product/{id}")
    public List<Price> getPricesOfProduct(@PathVariable long id) {
        return priceService.getAllPricesOfProduct(id);
    }

}
