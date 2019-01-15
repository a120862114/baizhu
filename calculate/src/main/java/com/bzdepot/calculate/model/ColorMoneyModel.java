package com.bzdepot.calculate.model;

import java.math.BigDecimal;

public class ColorMoneyModel {

    private int isOut;

    private BigDecimal ColorMoney;

    private BigDecimal SpotColorMoney;

    public int getIsOut() {
        return isOut;
    }

    public void setIsOut(int isOut) {
        this.isOut = isOut;
    }

    public BigDecimal getColorMoney() {
        return ColorMoney;
    }

    public void setColorMoney(BigDecimal colorMoney) {
        ColorMoney = colorMoney;
    }

    public BigDecimal getSpotColorMoney() {
        return SpotColorMoney;
    }

    public void setSpotColorMoney(BigDecimal spotColorMoney) {
        SpotColorMoney = spotColorMoney;
    }

    @Override
    public String toString() {
        return "ColorMoneyModel{" +
                "isOut=" + isOut +
                ", ColorMoney=" + ColorMoney +
                ", SpotColorMoney=" + SpotColorMoney +
                '}';
    }
}
