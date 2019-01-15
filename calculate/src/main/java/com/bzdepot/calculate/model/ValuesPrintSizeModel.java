package com.bzdepot.calculate.model;

public class ValuesPrintSizeModel {

    private Integer num;

    private String size;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "ValuesPrintSizeModel{" +
                "num=" + num +
                ", size='" + size + '\'' +
                '}';
    }
}
