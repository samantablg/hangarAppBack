package com.myApp.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.myApp.model.Product;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByStateTrue(Pageable pageable);

    /*@Query("SELECT p FROM  Product p WHERE p.name = ?1 and p.com.myHangar.hangar =?2")
    Product findProductByNameAndAndHangar(String name, Hangar com.myHangar.hangar);*/

    @Query("select p from Product p where p.name = ?1")
    Product findProductByName(String name);

    @Query("select p from Product p where p.name =?1 and p.description =?2")
    Product findProductByNameAndDescription(String name, String description);

    @Query("Select product FROM Product product WHERE product.name LIKE :name% AND product.state = true")
    List<Product> findByNameWithTrueState(@Param("name") String name);

    @Query(value = "SELECT product.name FROM Product product WHERE product.id =?1")
    String getNameOfProduct(long id);

}