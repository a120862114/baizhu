package com.bzdepot.calculate.model;

import java.math.BigDecimal;
import java.util.List;

public class BestPrintSizeModel {

    private Integer nums; //最好的拼版数量

    private Integer wastageNums; //纸张耗损数量

    private Integer printWastageNums; //印刷耗损

    private List<String> sizeList; //尺寸列表

    private List<String> lastSizeList; //计算后的尺寸列表

    private List<BigDecimal> moneyList; //原料金额

    private List<Byte> sizeTypeList; //原料纸张类型

    private List<Long> idList;

    private List<Integer> indexList;

    private List<BigDecimal> widthList; //原料纸张的宽度

    private List<BigDecimal> lengthList; //原料纸张的长度

    private List<Integer> yuanCaiLiaoNumsList; //原材料列表

    private List<BigDecimal> lastMoney; //包含印刷费的价格

    public List<BigDecimal> getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(List<BigDecimal> lastMoney) {
        this.lastMoney = lastMoney;
    }

    public List<Integer> getYuanCaiLiaoNumsList() {
        return yuanCaiLiaoNumsList;
    }

    public void setYuanCaiLiaoNumsList(List<Integer> yuanCaiLiaoNumsList) {
        this.yuanCaiLiaoNumsList = yuanCaiLiaoNumsList;
    }

    public Integer getWastageNums() {
        return wastageNums;
    }

    public void setWastageNums(Integer wastageNums) {
        this.wastageNums = wastageNums;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public List<String> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<String> sizeList) {
        this.sizeList = sizeList;
    }

    public List<BigDecimal> getMoneyList() {
        return moneyList;
    }

    public void setMoneyList(List<BigDecimal> moneyList) {
        this.moneyList = moneyList;
    }

    public List<Byte> getSizeTypeList() {
        return sizeTypeList;
    }

    public void setSizeTypeList(List<Byte> sizeTypeList) {
        this.sizeTypeList = sizeTypeList;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public List<Integer> getIndexList() {
        return indexList;
    }

    public void setIndexList(List<Integer> indexList) {
        this.indexList = indexList;
    }

    public List<BigDecimal> getWidthList() {
        return widthList;
    }

    public void setWidthList(List<BigDecimal> widthList) {
        this.widthList = widthList;
    }

    public List<BigDecimal> getLengthList() {
        return lengthList;
    }

    public void setLengthList(List<BigDecimal> lengthList) {
        this.lengthList = lengthList;
    }

    public List<String> getLastSizeList() {
        return lastSizeList;
    }

    public void setLastSizeList(List<String> lastSizeList) {
        this.lastSizeList = lastSizeList;
    }

    public Integer getPrintWastageNums() {
        return printWastageNums;
    }

    public void setPrintWastageNums(Integer printWastageNums) {
        this.printWastageNums = printWastageNums;
    }

    @Override
    public String toString() {
        return "BestPrintSizeModel{" +
                "nums=" + nums +
                ", wastageNums=" + wastageNums +
                ", printWastageNums=" + printWastageNums +
                ", sizeList=" + sizeList +
                ", lastSizeList=" + lastSizeList +
                ", moneyList=" + moneyList +
                ", sizeTypeList=" + sizeTypeList +
                ", idList=" + idList +
                ", indexList=" + indexList +
                ", widthList=" + widthList +
                ", lengthList=" + lengthList +
                ", yuanCaiLiaoNumsList=" + yuanCaiLiaoNumsList +
                ", lastMoney=" + lastMoney +
                '}';
    }
}
