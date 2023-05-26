package com.jayptl08.microservice.orderservice.dto;

import java.util.List;

public class StockCheckResClient {

    private boolean completed;

    private List<StockCheckRes> stockCheckRes;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public List<StockCheckRes> getStockCheckRes() {
        return stockCheckRes;
    }

    public void setStockCheckRes(List<StockCheckRes> stockCheckRes) {
        this.stockCheckRes = stockCheckRes;
    }

    public StockCheckResClient() {
    }

    public StockCheckResClient(boolean completed, List<StockCheckRes> stockCheckRes) {
        this.completed = completed;
        this.stockCheckRes = stockCheckRes;
    }

}
