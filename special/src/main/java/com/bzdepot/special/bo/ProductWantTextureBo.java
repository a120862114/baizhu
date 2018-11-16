package com.bzdepot.special.bo;

import com.bzdepot.special.model.PaperTexture;

public class ProductWantTextureBo {

    private Long id;

    private Long sellerId;

    private Long paperTid;

    private PaperTexture paperData;

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

    public Long getPaperTid() {
        return paperTid;
    }

    public void setPaperTid(Long paperTid) {
        this.paperTid = paperTid;
    }

    public PaperTexture getPaperData() {
        return paperData;
    }

    public void setPaperData(PaperTexture paperData) {
        this.paperData = paperData;
    }

    @Override
    public String toString() {
        return "ProductWantTextureBo{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", paperTid=" + paperTid +
                ", paperData=" + paperData +
                '}';
    }
}
