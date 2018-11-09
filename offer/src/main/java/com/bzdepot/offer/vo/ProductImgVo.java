package com.bzdepot.offer.vo;

public class ProductImgVo {

    private Long id;

    private String imgIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgIds() {
        return imgIds;
    }

    public void setImgIds(String imgIds) {
        this.imgIds = imgIds;
    }

    @Override
    public String toString() {
        return "ProductImgVo{" +
                "id=" + id +
                ", imgIds='" + imgIds + '\'' +
                '}';
    }
}
