package com.bzdepot.offer.vo;

import java.util.List;

public class ClassGroupVo {

    private Long id;

    private String groupName;

    private Long sellerId;

    private List<ClassfiyVo> classfiy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public List<ClassfiyVo> getClassfiy() {
        return classfiy;
    }

    public void setClassfiy(List<ClassfiyVo> classfiy) {
        this.classfiy = classfiy;
    }
}
