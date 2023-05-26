package com.jayptl08.microservice.orderservice.Proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jayptl08.microservice.orderservice.dto.OrderItemReq;
import com.jayptl08.microservice.orderservice.dto.StockCheckRes;

@FeignClient(name = "product-catalog-service")
public interface ProductCatalogProxy {
    
    @PostMapping("/product-catalog-service/product/stock/check")
    public List<StockCheckRes> checkStockInProductCatalog(@RequestBody List<OrderItemReq> orderItemReqs);

    @PostMapping("/product-catalog-service/product/stock/update")
    public boolean updateStockInProductCatalog(@RequestBody List<OrderItemReq> orderItemReq);


}
