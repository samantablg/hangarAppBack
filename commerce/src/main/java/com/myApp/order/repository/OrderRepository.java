package com.myApp.order.repository;


import com.myApp.model.UserProfile;
import com.myApp.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByProfile(UserProfile profile);

    Order findOrderByIdAndProfile(long id, UserProfile profile);

    boolean existsByProfile(UserProfile profile);

    boolean existsByIdAndProfile(long id, UserProfile profile);
}