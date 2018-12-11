package com.bzdepot.special.model;

public class ProductNumber {
    private Long id;

    private Long sellerId;

    private Integer nums;

    private Long pconfigId;

    private Byte isDefault;

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

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Long getPconfigId() {
        return pconfigId;
    }

    public void setPconfigId(Long pconfigId) {
        this.pconfigId = pconfigId;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public String toString() {
        return "ProductNumber{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", nums=" + nums +
                ", pconfigId=" + pconfigId +
                ", isDefault=" + isDefault +
                '}';
    }
}