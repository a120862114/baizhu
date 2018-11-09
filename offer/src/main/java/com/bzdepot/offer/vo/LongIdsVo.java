package com.bzdepot.offer.vo;

import java.util.Arrays;

public class LongIdsVo {

    private Long[] ids;

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "LongIdsVo{" +
                "ids=" + Arrays.toString(ids) +
                '}';
    }
}
