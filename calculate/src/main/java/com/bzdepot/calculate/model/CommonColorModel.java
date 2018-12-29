package com.bzdepot.calculate.model;

public class CommonColorModel {
    private Long id;

    private String colorName;

    private String colorBs;

    private Integer colorNumber;

    public Integer getColorNumber() {
        return colorNumber;
    }

    public void setColorNumber(Integer colorNumber) {
        this.colorNumber = colorNumber;
    }

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