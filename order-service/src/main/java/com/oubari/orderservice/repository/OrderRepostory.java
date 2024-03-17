package com.oubari.orderservice.repository;

import com.oubari.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepostory extends JpaRepository<Order,Long> {

}
