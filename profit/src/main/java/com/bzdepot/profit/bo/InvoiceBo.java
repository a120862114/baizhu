package com.bzdepot.profit.bo;

import com.bzdepot.profit.model.Invoice;

import java.util.Arrays;

public class InvoiceBo {

    private Long sellerId;

    private Invoice[] config;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Invoice[] getConfig() {
        return config;
    }

    public void setConfig(Invoice[] config) {
        this.config = config;
    }

    @Override
    public String toString() {
        return "InvoiceBo{" +
                "sellerId=" + sellerId +
                ", config=" + Arrays.toString(config) +
                '}';
    }
}
