package com.bzdepot.client.vo;

import javax.validation.constraints.NotNull;

public class LevelModuleVo {

    @NotNull(message = "商家编号不能为空!")
    private Long sellerId;

    private Long levelId;

    private int pageSize;

    private int pageNum;

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
