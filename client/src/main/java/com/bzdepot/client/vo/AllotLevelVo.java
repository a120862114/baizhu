package com.bzdepot.client.vo;

import javax.validation.constraints.NotNull;

public class AllotLevelVo {

    @NotNull(message = "用户编号不能为空!")
    private Long userId;

    @NotNull(message = "旧用户等级编号不能为空!")
    private Long oldLevelId;

    @NotNull(message = "新用户等级编号不能为空!")
    private Long toLevelId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOldLevelId() {
        return oldLevelId;
    }

    public void setOldLevelId(Long oldLevelId) {
        this.oldLevelId = oldLevelId;
    }

    public Long getToLevelId() {
        return toLevelId;
    }

    public void setToLevelId(Long toLevelId) {
        this.toLevelId = toLevelId;
    }
}
