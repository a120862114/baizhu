package com.bzdepot.offer.vo;

import java.util.List;

public class TextureGroupVo {

    private Long id;

    private String groupName;

    private Long sellerId;

    private List<TextureVo> texture;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public List<TextureVo> getTexture() {
        return texture;
    }

    public void setTexture(List<TextureVo> texture) {
        this.texture = texture;
    }
}
