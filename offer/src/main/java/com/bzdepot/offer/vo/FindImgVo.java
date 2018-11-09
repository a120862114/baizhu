package com.bzdepot.offer.vo;

import javax.validation.constraints.NotNull;

public class FindImgVo {

    @NotNull(message = "商家编号不能为空!")
    private Long seller_id;

    @NotNull(message = "分类编号不能为空!")
    private Long class_id;

    @NotNull(message = "材质编号不能为空!")
    private Long texture_id;

    @NotNull(message = "报价配置类型不能为空!")
    private Byte types;

    public Long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(Long seller_id) {
        this.seller_id = seller_id;
    }

    public Long getClass_id() {
        return class_id;
    }

    public void setClass_id(Long class_id) {
        this.class_id = class_id;
    }

    public Long getTexture_id() {
        return texture_id;
    }

    public void setTexture_id(Long texture_id) {
        this.texture_id = texture_id;
    }

    public Byte getTypes() {
        return types;
    }

    public void setTypes(Byte types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "FindImgVo{" +
                "seller_id=" + seller_id +
                ", class_id=" + class_id +
                ", texture_id=" + texture_id +
                ", types=" + types +
                '}';
    }
}
