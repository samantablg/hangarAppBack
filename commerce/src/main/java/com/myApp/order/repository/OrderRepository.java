package com.myApp.order.repository;


import com.myApp.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByProfile(long profile);

    Boolean existsOrdersByProfile(long profile);
}