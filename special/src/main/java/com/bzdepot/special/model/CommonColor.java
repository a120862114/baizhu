package com.bzdepot.special.model;

public class CommonColor {
    private Long id;

    private String colorName;

    private String colorBs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName == null ? null : colorName.trim();
    }

    public String getColorBs() {
        return colorBs;
    }

    public void setColorBs(String colorBs) {
        this.colorBs = colorBs == null ? null : colorBs.trim();
    }
}