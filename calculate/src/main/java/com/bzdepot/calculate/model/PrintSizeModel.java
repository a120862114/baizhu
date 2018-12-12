package com.bzdepot.calculate.model;

import java.util.List;

public class PrintSizeModel {

    private Integer num;

    private List<String> list;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PrintSizeModel{" +
                "num=" + num +
                ", list=" + list +
                '}';
    }
}
