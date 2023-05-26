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
import com.jayptl08.microservice.orderservice.dto.StockCheckRes;
import com.jayptl08.microservice.orderservice.dto.StockCheckResClient;
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
    public StockCheckResClient placeOrder(@RequestBody Orders orders) {
        // if(productCatalogProxy.checkStockInProductCatalog(converter(orders.getOrderItems()))){
        // if (updateStockInProductCatalog(orders.getOrderItems())) {
        // service.placeOrder(orders);
        // }
        // }

        List<StockCheckRes> stockCheckRes = productCatalogProxy
                .checkStockInProductCatalog(converter(orders.getOrderItems()));

        List<StockCheckRes> rejectedItems = new ArrayList<>();
        StockCheckResClient stockCheckResClients = new StockCheckResClient(false, null);

        int j = 0;
        for (int i = 0; i < stockCheckRes.size(); i++) {

            if (stockCheckRes.get(i).getQuantity() > stockCheckRes.get(i).getStock()) {
                stockCheckResClients.setCompleted(false);
                rejectedItems.add(stockCheckRes.get(i));
                j++;
            }

            else if (stockCheckRes.get(i).getQuantity() <= stockCheckRes.get(i).getStock()) {
                if (j <= 0) {
                    stockCheckResClients.setCompleted(true);
                }

            }
            stockCheckResClients.setStockCheckRes(rejectedItems);
        }

        if (j <= 0) {
            if (updateStockInProductCatalog(orders.getOrderItems())) {
                service.placeOrder(orders);
            }
        }

        return stockCheckResClients;
    }

    @GetMapping("/id/{id}")
    public List<Orders> getOrders(@PathVariable Long id) {
        return service.getOrders(id);
    }

    private boolean updateStockInProductCatalog(List<OrderItems> orderItems) {
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
