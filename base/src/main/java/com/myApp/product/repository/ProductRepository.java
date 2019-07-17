package com.myApp.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.myApp.product.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /*@Query("SELECT p FROM  Product p WHERE p.name = ?1 and p.com.myHangar.hangar =?2")
    Product findProductByNameAndAndHangar(String name, Hangar com.myHangar.hangar);*/

    @Query("select p from Product p where p.name = ?1")
    Product findProductByName(String name);


}