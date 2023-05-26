package com.jayptl08.microservice.productcatalogservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayptl08.microservice.productcatalogservice.dto.OrderItemReq;
import com.jayptl08.microservice.productcatalogservice.dto.StockCheckRes;
import com.jayptl08.microservice.productcatalogservice.model.ProductCatalog;
import com.jayptl08.microservice.productcatalogservice.repository.ProductCatalogRepository;

@Service
public class ProductCatalogService {

    @Autowired
    private ProductCatalogRepository repository;

    public List<ProductCatalog> getAllProducts() {
        return repository.findAll();
    }

    public ProductCatalog getAProduct(Long id) {
        return repository.findById(id).get();
    }

    public List<ProductCatalog> getAllProductsByCateory(String catagory) {
        return repository.findByCatagory(catagory);
    }

    public ProductCatalog addProductCatalog(ProductCatalog productCatalog) {
        return repository.save(productCatalog);
    }

    public void deleteProductById(Long id) {
        repository.deleteById(id);
    }

    public void updateStock(List<OrderItemReq> orderItems) {
        for (int i = 0; i < orderItems.size(); i++) {
            Long productId = orderItems.get(i).getProductId();
            // System.out.println(productId);
            Integer quantity = orderItems.get(i).getQuantity();
            ProductCatalog productCatalog = repository.findById(productId).get();
            productCatalog.setStock(productCatalog.getStock() - quantity);
            repository.save(productCatalog);

            // repository.findById(id).get().setStock(repository.findById(id).get().getStock()
            // - quantity);
        }
    }

    public List<StockCheckRes> checkStock(List<OrderItemReq> orderItems) {

        List<StockCheckRes> stockCheckRes = new ArrayList<>();

        for (int i = 0; i < orderItems.size(); i++) {
            Long productId = orderItems.get(i).getProductId();
            Integer quantity = orderItems.get(i).getQuantity();
            Integer stock = repository.findById(productId).get().getStock();
            StockCheckRes stockCheckRes2 = new StockCheckRes(productId, quantity, stock);
            stockCheckRes.add(i, stockCheckRes2);
        }
        return stockCheckRes;
    }

}
