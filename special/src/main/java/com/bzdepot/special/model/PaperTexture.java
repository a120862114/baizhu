package com.bzdepot.special.model;

public class PaperTexture {
    private Long id;

    private Long sellerId;

    private String names;

    private String tBs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names == null ? null : names.trim();
    }

    public String gettBs() {
        return tBs;
    }

    public void settBs(String tBs) {
        this.tBs = tBs == null ? null : tBs.trim();
    }
}