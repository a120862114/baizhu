package com.bzdepot.transport.model;

import java.util.List;

public class BlockGroupData {
    private Long id;

    private Long sellerId;

    private Long companyId;

    private Long regionId;

    private Integer blockId;

    private String cityids;

    private String cityname;

    private String pid;

    private List<BlockPrice> pricedata;

    private List<RegionBlock> suspendData;

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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public String getCityids() {
        return cityids;
    }

    public void setCityids(String cityids) {
        this.cityids = cityids;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<BlockPrice> getPricedata() {
        return pricedata;
    }

    public void setPricedata(List<BlockPrice> pricedata) {
        this.pricedata = pricedata;
    }

    public List<RegionBlock> getSuspendData() {
        return suspendData;
    }

    public void setSuspendData(List<RegionBlock> suspendData) {
        this.suspendData = suspendData;
    }

    @Override
    public String toString() {
        return "BlockGroupData{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", companyId=" + companyId +
                ", regionId=" + regionId +
                ", blockId=" + blockId +
                ", cityids='" + cityids + '\'' +
                ", cityname='" + cityname + '\'' +
                ", pid='" + pid + '\'' +
                ", pricedata=" + pricedata +
                ", suspendData=" + suspendData +
                '}';
    }
}