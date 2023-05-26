package com.jayptl08.microservice.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayptl08.microservice.orderservice.model.Orders;
import com.jayptl08.microservice.orderservice.repository.OrdersRepository;

@Service
public class OrderService {

    @Autowired
    private OrdersRepository repository;

    public void placeOrder(Orders orders){
        repository.save(orders);
    }


    public List<Orders> getOrders(Long id){
        return repository.findAllById(id);
    }

    

}

