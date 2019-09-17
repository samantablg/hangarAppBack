package com.myApp.price.repository;

import com.myApp.price.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.myApp.model.Product;

import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("SELECT p FROM Price p WHERE p.product = ?1")
    List<Price> findPricesByProduct(Product product);

    @Query(value = "SELECT * FROM Price p WHERE p.product = ?1 order by p.date desc limit 1", nativeQuery = true)
    Price getLastPriceOfProduct(long product);

    @Query("SELECT p FROM Price p WHERE p.product = ?1")
    Price existsPriceByProduct(Product product);

}
