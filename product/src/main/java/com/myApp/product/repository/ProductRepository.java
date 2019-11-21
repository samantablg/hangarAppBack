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

    Page<Product>  findAllByStateIsTrue(Pageable pageable);

    @Query("select p from Product p where p.name = ?1")
    Product findProductByName(String name);

    @Query("select p from Product p where p.name =?1 and p.description =?2")
    Product findProductByNameAndDescription(String name, String description);

    @Query("Select product FROM Product product WHERE product.name LIKE :name% AND product.state = true")
    List<Product> findByNameWithTrueState(@Param("name") String name);

    @Query(value = "SELECT product.name FROM Product product WHERE product.id =?1")
    String getNameOfProduct(long id);

    boolean existsByName(String name);

    boolean existsByNameAndDescription(String name, String description);

    @Query("select p from Product p where p.state = true")
    List<Product> findAllWithTrueState();

}