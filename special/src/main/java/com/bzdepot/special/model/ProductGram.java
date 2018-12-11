package com.bzdepot.special.model;

public class ProductGram {
    private Long id;

    private Long ptId;

    private Long sellerId;

    private Long gramId;

    private Byte isDefault;

    private PaperGram gramData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPtId() {
        return ptId;
    }

    public void setPtId(Long ptId) {
        this.ptId = ptId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getGramId() {
        return gramId;
    }

    public void setGramId(Long gramId) {
        this.gramId = gramId;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public PaperGram getGramData() {
        return gramData;
    }

    public void setGramData(PaperGram gramData) {
        this.gramData = gramData;
    }

    @Override
    public String toString() {
        return "ProductGram{" +
                "id=" + id +
                ", ptId=" + ptId +
                ", sellerId=" + sellerId +
                ", gramId=" + gramId +
                ", isDefault=" + isDefault +
                ", gramData=" + gramData +
                '}';
    }
}