package com.bzdepot.profit.model;

public class ProfitRuler {
    private Long id;

    private Long offerId;

    private Byte rulerType;

    private Byte dafaultType;

    private Long createTime;

    private String rulerIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }

    public Byte getRulerType() {
        return rulerType;
    }

    public void setRulerType(Byte rulerType) {
        this.rulerType = rulerType;
    }

    public Byte getDafaultType() {
        return dafaultType;
    }

    public void setDafaultType(Byte dafaultType) {
        this.dafaultType = dafaultType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getRulerIds() {
        return rulerIds;
    }

    public void setRulerIds(String rulerIds) {
        this.rulerIds = rulerIds == null ? null : rulerIds.trim();
    }
}