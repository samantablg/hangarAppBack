package com.myApp.product_order.repository;

import com.myApp.model.Product_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Product_OrderRepository extends JpaRepository<Product_Order, Long> {
}
