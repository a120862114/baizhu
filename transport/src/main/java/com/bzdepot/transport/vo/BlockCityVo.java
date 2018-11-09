package com.bzdepot.transport.vo;

public class BlockCityVo {

    private Long id;

    private Long cityId;

    private String cityName;

    private Integer blockId;

    private Byte isSuspend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public Byte getIsSuspend() {
        return isSuspend;
    }

    public void setIsSuspend(Byte isSuspend) {
        this.isSuspend = isSuspend;
    }

    @Override
    public String toString() {
        return "BlockCityVo{" +
                "id=" + id +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", blockId=" + blockId +
                ", isSuspend=" + isSuspend +
                '}';
    }
}
