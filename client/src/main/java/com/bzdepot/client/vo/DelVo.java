package com.bzdepot.client.vo;

import javax.validation.constraints.NotNull;

public class DelVo {

    @NotNull(message = "等级编号不能为空!")
    private Long levelId;

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }
}
