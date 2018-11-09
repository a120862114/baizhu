package com.bzdepot.offer.vo;

import java.math.BigDecimal;
import java.util.Arrays;

public class EditDetailVo {

    private Long[] ids;

    private Integer nums;

    private BigDecimal xmoney;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public BigDecimal getXmoney() {
        return xmoney;
    }

    public void setXmoney(BigDecimal xmoney) {
        this.xmoney = xmoney;
    }

    @Override
    public String toString() {
        return "EditDetailVo{" +
                "ids=" + Arrays.toString(ids) +
                ", nums=" + nums +
                ", xmoney=" + xmoney +
                '}';
    }
}
