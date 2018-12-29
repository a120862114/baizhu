package com.bzdepot.calculate.model;

import java.math.BigDecimal;
import java.util.List;

public class PrintSizeModel {

    private Integer num;

    private List<String> list;

    private List<String> lastList;

    private List<BigDecimal> money;

    private List<Integer> yunCaiLiaoNums;

    public List<Integer> getYunCaiLiaoNums() {
        return yunCaiLiaoNums;
    }

    public void setYunCaiLiaoNums(List<Integer> yunCaiLiaoNums) {
        this.yunCaiLiaoNums = yunCaiLiaoNums;
    }

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

    public List<BigDecimal> getMoney() {
        return money;
    }

    public void setMoney(List<BigDecimal> money) {
        this.money = money;
    }

    public List<String> getLastList() {
        return lastList;
    }

    public void setLastList(List<String> lastList) {
        this.lastList = lastList;
    }

    @Override
    public String toString() {
        return "PrintSizeModel{" +
                "num=" + num +
                ", list=" + list +
                ", lastList=" + lastList +
                ", money=" + money +
                ", yunCaiLiaoNums=" + yunCaiLiaoNums +
                '}';
    }
}
