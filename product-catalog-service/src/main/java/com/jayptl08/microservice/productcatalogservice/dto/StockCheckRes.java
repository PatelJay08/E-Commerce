package com.jayptl08.microservice.productcatalogservice.dto;

public class StockCheckRes {

    private Long productId;
    private Integer quantity;
    private Integer stock;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public StockCheckRes() {
    }

    public StockCheckRes(Long productId, Integer quantity, Integer stock) {
        this.productId = productId;
        this.quantity = quantity;
        this.stock = stock;
    }

}
