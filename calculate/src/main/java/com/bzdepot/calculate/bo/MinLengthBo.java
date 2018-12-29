package com.bzdepot.calculate.bo;

public class MinLengthBo {

    private Integer cwidth;

    private Integer num;

    private Integer length;

    private Integer width;

    private boolean limited = true;

    public boolean isLimited() {
        return limited;
    }

    public void setLimited(boolean limited) {
        this.limited = limited;
    }

    public Integer getCwidth() {
        return cwidth;
    }

    public void setCwidth(Integer cwidth) {
        this.cwidth = cwidth;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
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
        return "MinLengthBo{" +
                "cwidth=" + cwidth +
                ", num=" + num +
                ", length=" + length +
                ", width=" + width +
                ", limited=" + limited +
                '}';
    }
}
