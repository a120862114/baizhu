package com.bzdepot.calculate.model;

import java.math.BigDecimal;
import java.util.List;

public class TaskPaperSizeModel {

    private Byte SizeType; // 0 正度 1大度 2 卷筒 3 其他尺寸

    private Long Id; //如果纸张为卷筒或其他尺寸时的ID编号

    private int index; //如果纸张为卷筒或其他尺寸时的索引数

    private BigDecimal length;

    private BigDecimal width;

    private Integer bestLength; //最好的拼版长度

    private Integer bestWidth; //最好的拼版宽度

    private Integer lastBestLength; //计算后最好的拼版长度

    private Integer lastBestWidth; //计算后最好的拼版宽度

    private Integer bestPinNum; //最好的拼版数量

    private Integer bestWastageNums; //纸张耗损数量

    private Integer bestPrintWastageNums; //纸张印刷耗损数量

    private Integer bestYuanCaiLiaoNums; //原材料数量

    private BigDecimal bestMoney; //原料费用

    private BigDecimal lastMoney;//最后的印刷费

    private List<PrintSizeModel> sizeList; //尺寸结合

    private PrintingCostModel printingCostModel;

    public Integer getBestYuanCaiLiaoNums() {
        return bestYuanCaiLiaoNums;
    }

    public void setBestYuanCaiLiaoNums(Integer bestYuanCaiLiaoNums) {
        this.bestYuanCaiLiaoNums = bestYuanCaiLiaoNums;
    }

    public BigDecimal getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(BigDecimal lastMoney) {
        this.lastMoney = lastMoney;
    }

    public Integer getBestWastageNums() {
        return bestWastageNums;
    }

    public void setBestWastageNums(Integer bestWastageNums) {
        this.bestWastageNums = bestWastageNums;
    }

    public PrintingCostModel getPrintingCostModel() {
        return printingCostModel;
    }

    public void setPrintingCostModel(PrintingCostModel printingCostModel) {
        this.printingCostModel = printingCostModel;
    }

    public List<PrintSizeModel> getSizeList() {
        return sizeList;
    }

    public void setSizeList(List<PrintSizeModel> sizeList) {
        this.sizeList = sizeList;
    }

    public Byte getSizeType() {
        return SizeType;
    }

    public void setSizeType(Byte sizeType) {
        SizeType = sizeType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public Integer getBestLength() {
        return bestLength;
    }

    public void setBestLength(Integer bestLength) {
        this.bestLength = bestLength;
    }

    public Integer getBestWidth() {
        return bestWidth;
    }

    public void setBestWidth(Integer bestWidth) {
        this.bestWidth = bestWidth;
    }

    public Integer getBestPinNum() {
        return bestPinNum;
    }

    public void setBestPinNum(Integer bestPinNum) {
        this.bestPinNum = bestPinNum;
    }

    public BigDecimal getBestMoney() {
        return bestMoney;
    }

    public void setBestMoney(BigDecimal bestMoney) {
        this.bestMoney = bestMoney;
    }

    public Integer getLastBestLength() {
        return lastBestLength;
    }

    public void setLastBestLength(Integer lastBestLength) {
        this.lastBestLength = lastBestLength;
    }

    public Integer getLastBestWidth() {
        return lastBestWidth;
    }

    public void setLastBestWidth(Integer lastBestWidth) {
        this.lastBestWidth = lastBestWidth;
    }

    public Integer getBestPrintWastageNums() {
        return bestPrintWastageNums;
    }

    public void setBestPrintWastageNums(Integer bestPrintWastageNums) {
        this.bestPrintWastageNums = bestPrintWastageNums;
    }

    @Override
    public String toString() {
        return "TaskPaperSizeModel{" +
                "SizeType=" + SizeType +
                ", Id=" + Id +
                ", index=" + index +
                ", length=" + length +
                ", width=" + width +
                ", bestLength=" + bestLength +
                ", bestWidth=" + bestWidth +
                ", lastBestLength=" + lastBestLength +
                ", lastBestWidth=" + lastBestWidth +
                ", bestPinNum=" + bestPinNum +
                ", bestWastageNums=" + bestWastageNums +
                ", bestPrintWastageNums=" + bestPrintWastageNums +
                ", bestYuanCaiLiaoNums=" + bestYuanCaiLiaoNums +
                ", bestMoney=" + bestMoney +
                ", lastMoney=" + lastMoney +
                ", sizeList=" + sizeList +
                ", printingCostModel=" + printingCostModel +
                '}';
    }
}
