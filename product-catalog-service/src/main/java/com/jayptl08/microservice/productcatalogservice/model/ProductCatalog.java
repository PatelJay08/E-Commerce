package com.jayptl08.microservice.productcatalogservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ProductCatalog {

    @Id
    @GeneratedValue
    private Long productId;

    private String title;
    private double price;
    private String catagory;
    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public Long getId() {
        return productId;
    }

    public void setId(Long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ProductCatalog(Long productId, String title, double price, String catagory, Integer stock) {
        this.productId = productId;
        this.title = title;
        this.price = price;
        this.catagory = catagory;
        this.stock = stock;
    }

    public ProductCatalog() {
    }

}
