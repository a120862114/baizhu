package com.bzdepot.transport.vo;

import com.bzdepot.transport.model.RegionBlock;

import java.util.List;

public class BlockResultVo {

    private Integer blockId;

    private List<RegionBlock> datas;

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }

    public List<RegionBlock> getDatas() {
        return datas;
    }

    public void setDatas(List<RegionBlock> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "BlockResultVo{" +
                "blockId=" + blockId +
                ", datas=" + datas +
                '}';
    }
}
