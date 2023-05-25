package com.jayptl08.microservice.productcatalogservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jayptl08.microservice.productcatalogservice.model.ProductCatalog;
import java.util.List;


public interface ProductCatalogRepository extends JpaRepository<ProductCatalog,Long> {
    List<ProductCatalog> findByCatagory(String catagory);
}