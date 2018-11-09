package com.bzdepot.images.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ListVo {

    @NotNull(message = "用户编号不能为空!")
    private Long user_id;

    private String img_name;

    private Long starttime;

    private Long endtime;

    private int pagesize;

    private int pagenum;
}
