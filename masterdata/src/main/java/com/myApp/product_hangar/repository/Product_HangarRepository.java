package com.myApp.product_hangar.repository;

import com.myApp.product_hangar.model.Product_Hangar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_HangarRepository extends JpaRepository<Product_Hangar, Long> {

    @Query("SELECT p_h FROM Product_Hangar p_h WHERE p_h.hangar =?1")
    List<Product_Hangar> findAllByHangar(long hangar);

    @Query("SELECT p_h FROM Product_Hangar p_h WHERE p_h.product =?1")
    List<Product_Hangar> findAllByProduct(long product);

    @Query("SELECT p_h FROM Product_Hangar p_h WHERE p_h.product =?1 AND p_h.hangar =?2")
    Product_Hangar findByProductAndHangar(long product, long hangar);

    boolean existsByHangarAndProduct(long hangar, long product);

    boolean existsByProduct(long product);

    boolean existsByHangar(long hangar);

    List<Product_Hangar> findProduct_HangarsByHangarIsNotLike(long hangar); //devuelve los que no están asociados a ese hangar pero si están asociados a algún hangar
}
