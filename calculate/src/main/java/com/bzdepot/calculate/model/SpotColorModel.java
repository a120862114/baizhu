package com.bzdepot.calculate.model;

public class SpotColorModel {
    private Long id;

    private String spotColorName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpotColorName() {
        return spotColorName;
    }

    public void setSpotColorName(String spotColorName) {
        this.spotColorName = spotColorName == null ? null : spotColorName.trim();
    }
}