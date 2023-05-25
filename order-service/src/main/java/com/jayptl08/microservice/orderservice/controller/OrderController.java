package com.jayptl08.microservice.orderservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayptl08.microservice.orderservice.Proxy.ProductCatalogProxy;
import com.jayptl08.microservice.orderservice.dto.OrderItemReq;
import com.jayptl08.microservice.orderservice.model.OrderItems;
import com.jayptl08.microservice.orderservice.model.Orders;
import com.jayptl08.microservice.orderservice.service.OrderService;

@RestController
@RequestMapping("/order-service")
public class OrderController {

    @Autowired
    private ProductCatalogProxy productCatalogProxy;
    @Autowired
    private OrderService service;

    @PostMapping("/place-order")
    public void placeOrder(@RequestBody Orders orders) {
        if(productCatalogProxy.checkStockInProductCatalog(converter(orders.getOrderItems()))){
            if (updateStockInProductCatalog(orders.getOrderItems())) {
                service.placeOrder(orders);
            }
        }
    }

    @GetMapping("/id/{id}")
    public List<Orders> getOrders(@PathVariable Long id) {
        return service.getOrders(id);
    }

    public boolean updateStockInProductCatalog(List<OrderItems> orderItems) {
        List<OrderItemReq> orderItemsreq = converter(orderItems);
        return productCatalogProxy.updateStockInProductCatalog(orderItemsreq);
    }

    private List<OrderItemReq> converter(List<OrderItems> orderItems) {
        List<OrderItemReq> orderItemsreq = new ArrayList<>();
        for (int i = 0; i < orderItems.size(); i++) {
            OrderItemReq orderItemReq1 = new OrderItemReq(
                    orderItems.get(i).getProductId(),
                    orderItems.get(i).getName(),
                    orderItems.get(i).getPrice(),
                    orderItems.get(i).getQuantity());
                    orderItemsreq.add(i, orderItemReq1);
        }
        return orderItemsreq;
    }

}
