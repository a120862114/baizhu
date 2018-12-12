package com.bzdepot.calculate.bo;

public class PapercutNumber {

    private Integer clength;

    private Integer cwidth;

    private Integer length;

    private Integer width;

    public Integer getClength() {
        return clength;
    }

    public void setClength(Integer clength) {
        this.clength = clength;
    }

    public Integer getCwidth() {
        return cwidth;
    }

    public void setCwidth(Integer cwidth) {
        this.cwidth = cwidth;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "PapercutNumber{" +
                "clength=" + clength +
                ", cwidth=" + cwidth +
                ", length=" + length +
                ", width=" + width +
                '}';
    }
}
