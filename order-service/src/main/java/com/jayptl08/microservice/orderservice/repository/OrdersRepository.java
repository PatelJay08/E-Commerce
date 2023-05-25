package com.jayptl08.microservice.orderservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayptl08.microservice.orderservice.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
    
    List<Orders> findAllById(Long id);

}
