package com.jayptl08.microservice.productcatalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jayptl08.microservice.productcatalogservice.dto.OrderItemReq;
import com.jayptl08.microservice.productcatalogservice.dto.StockCheckRes;
import com.jayptl08.microservice.productcatalogservice.model.ProductCatalog;
import com.jayptl08.microservice.productcatalogservice.service.ProductCatalogService;

@RestController
@RequestMapping("/product-catalog-service")
public class ProductCatalogController {

    @Autowired
    private ProductCatalogService service;

    @GetMapping("/product/all")
    public List<ProductCatalog> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/product/catagory/{catagory}")
    public List<ProductCatalog> getProductsByCatagory(@PathVariable String catagory) {
        return service.getAllProductsByCateory(catagory);
    }

    @GetMapping("/product/id/{id}")
    public ProductCatalog getAProduct(@PathVariable Long id) {
        return service.getAProduct(id);
    }

    @PostMapping("/product/new")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductCatalog addNewProductCatalog(@RequestBody ProductCatalog productCatalog) {
        return service.addProductCatalog(productCatalog);
    }

    @DeleteMapping("/product/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProductById(@PathVariable Long id) {
        service.deleteProductById(id);
    }

    @PostMapping("/product/stock/update")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateStock(@RequestBody List<OrderItemReq> orderItems) {
        // System.out.println(orderItems.get(0).getProductId());
        service.updateStock(orderItems);
        return true;
    }

    @PostMapping("/product/stock/check")
    @ResponseStatus(HttpStatus.OK)
    public List<StockCheckRes> checkStock(@RequestBody List<OrderItemReq> orderItems) {
        return service.checkStock(orderItems);
    }

}
