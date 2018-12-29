package com.bzdepot.calculate.model;

import java.math.BigDecimal;
import java.util.List;

public class PaperCostModel {
    private Long id;

    private Long sellerId;

    private Long paperTid;

    private String paperTname;

    private Long brandId;

    private String brandName;

    private Long packingId;

    private String packingName;

    private Long gramId;

    private String gramName;

    private BigDecimal thicknessStart;

    private BigDecimal thicknessEnd;

    private Long supplierId;

    private String supplierName;

    private Byte status;

    private Long createTime;

    private Long updateTime;

    private Byte isMagnanimity;

    private Byte isPositiveDegree;

    private Byte isRoutine;

    private BigDecimal lastDun;

    private BigDecimal lastMoney;

    private Byte lastUnitType;




    private List<PrintingCostModel> printingData;

    public BigDecimal getLastDun() {
        return lastDun;
    }

    public void setLastDun(BigDecimal lastDun) {
        this.lastDun = lastDun;
    }

    public BigDecimal getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(BigDecimal lastMoney) {
        this.lastMoney = lastMoney;
    }

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

    public String getPaperTname() {
        return paperTname;
    }

    public void setPaperTname(String paperTname) {
        this.paperTname = paperTname == null ? null : paperTname.trim();
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Long getPackingId() {
        return packingId;
    }

    public void setPackingId(Long packingId) {
        this.packingId = packingId;
    }

    public String getPackingName() {
        return packingName;
    }

    public void setPackingName(String packingName) {
        this.packingName = packingName == null ? null : packingName.trim();
    }

    public Long getGramId() {
        return gramId;
    }

    public void setGramId(Long gramId) {
        this.gramId = gramId;
    }

    public String getGramName() {
        return gramName;
    }

    public void setGramName(String gramName) {
        this.gramName = gramName == null ? null : gramName.trim();
    }

    public BigDecimal getThicknessStart() {
        return thicknessStart;
    }

    public void setThicknessStart(BigDecimal thicknessStart) {
        this.thicknessStart = thicknessStart;
    }

    public BigDecimal getThicknessEnd() {
        return thicknessEnd;
    }

    public void setThicknessEnd(BigDecimal thicknessEnd) {
        this.thicknessEnd = thicknessEnd;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getIsMagnanimity() {
        return isMagnanimity;
    }

    public void setIsMagnanimity(Byte isMagnanimity) {
        this.isMagnanimity = isMagnanimity;
    }

    public Byte getIsPositiveDegree() {
        return isPositiveDegree;
    }

    public void setIsPositiveDegree(Byte isPositiveDegree) {
        this.isPositiveDegree = isPositiveDegree;
    }

    public Byte getIsRoutine() {
        return isRoutine;
    }

    public void setIsRoutine(Byte isRoutine) {
        this.isRoutine = isRoutine;
    }

    public Byte getLastUnitType() {
        return lastUnitType;
    }

    public void setLastUnitType(Byte lastUnitType) {
        this.lastUnitType = lastUnitType;
    }

    public List<PrintingCostModel> getPrintingData() {
        return printingData;
    }

    public void setPrintingData(List<PrintingCostModel> printingData) {
        this.printingData = printingData;
    }


}