package com.bzdepot.calculate.bo;

/**
 * 专版报价的专色请求参数
 */
public class SpotColorBo {

    private Long spotColorId; //专色编号

    private Byte isOnBlock; //是否开启包含满版打色块 0否 1是

    public Long getSpotColorId() {
        return spotColorId;
    }

    public void setSpotColorId(Long spotColorId) {
        this.spotColorId = spotColorId;
    }

    public Byte getIsOnBlock() {
        return isOnBlock;
    }

    public void setIsOnBlock(Byte isOnBlock) {
        this.isOnBlock = isOnBlock;
    }

    @Override
    public String toString() {
        return "SpotColorBo{" +
                "spotColorId=" + spotColorId +
                ", isOnBlock=" + isOnBlock +
                '}';
    }
}
