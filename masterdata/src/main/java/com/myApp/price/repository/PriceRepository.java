package com.myApp.price.repository;

import com.myApp.price.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.myApp.product.model.Product;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.product = ?1 and p.price =?2")
    Price findByProductAndPrice(Product product, float price);

    @Query("SELECT p FROM Price p WHERE p.product = ?1")
    List<Price> findPricesByProduct(Product product);

    @Query("SELECT p FROM Price p WHERE p.product = ?1")
    List<Price> findByProductOrderByDateDesc(Product product);

}
