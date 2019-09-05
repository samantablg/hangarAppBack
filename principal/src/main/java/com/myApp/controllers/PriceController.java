package com.myApp.controllers;

import com.myApp.exceptions.ControllerException;
import com.myApp.price.builder.PriceDtoBuilder;
import com.myApp.price.dto.PriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.myApp.price.model.Price;
import com.myApp.price.service.PriceServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/price")
public class PriceController {

    @Autowired
    PriceServiceImpl priceService;

    @GetMapping("/prices")
    public ResponseEntity<List<PriceDto>> getPrices() {
        List<Price> prices = priceService.getAllPrices();

        return new ResponseEntity<>(
                prices.stream().map(
                        price -> new PriceDtoBuilder(price).getPriceDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @PostMapping("/product/{id}")
    public ResponseEntity<PriceDto> priceToProduct(@PathVariable long id, @RequestBody float price) {
        Price newPrice =  priceService.createEntryPriceToProduct(id, price);
        return new ResponseEntity<>(
                new PriceDtoBuilder(newPrice).getPriceDto(),
                HttpStatus.OK
        );
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<PriceDto>> getPricesOfProduct(@PathVariable long id) {
        List<Price> prices = priceService.getAllPricesOfProduct(id);
        return new ResponseEntity<>(
                prices.stream().map(
                      price -> new PriceDtoBuilder(price).getPriceDto()).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    @GetMapping("last/{id}")
    public ResponseEntity<Price> getLastPriceOfProduct(@PathVariable long id) {
        if(id<=0)
            throw new ControllerException.idNotAllowed(id);

        return new ResponseEntity<>(priceService.getCurrentPriceOfProduct(id), HttpStatus.OK);
    }

}
