package com.bzdepot.calculate.bo;

import java.util.List;

/**
 * 专版报价的颜色相关请求参数
 */
public class ColorBo {

    private Byte sided; //1 单面 2 双面

    private Byte isGlossOil; //是否勾选了光油选项 0 否 1是

    private Byte isIdentical; //是否双面图文内容完全相同 0否 1是

    /**
     * 颜色类型说明:
     *                1  普通单色
     *                2  普通双色
     *                3  彩色
     *                4  彩色+专色
     *                5  只印专色
     *  注意：当只有一个 颜色类型时 传递 firstColorType , 当双面不同配置时 分别传递 firstColorType 与 secondColorType
     */
    private Integer firstColorType;

    private Integer secondColorType;

    /**
     * 专色参数说明:
     * 注意：当只有一个 颜色类型时 传递 firstSpotColorList , 当双面不同配置时 分别传递 firstSpotColorList 与 secondSpotColorList
     */
    private List<SpotColorBo> firstSpotColorList;

    private List<SpotColorBo> secondSpotColorList;

    public Byte getSided() {
        return sided;
    }

    public void setSided(Byte sided) {
        this.sided = sided;
    }

    public Byte getIsGlossOil() {
        return isGlossOil;
    }

    public void setIsGlossOil(Byte isGlossOil) {
        this.isGlossOil = isGlossOil;
    }

    public Byte getIsIdentical() {
        return isIdentical;
    }

    public void setIsIdentical(Byte isIdentical) {
        this.isIdentical = isIdentical;
    }

    public Integer getFirstColorType() {
        return firstColorType;
    }

    public void setFirstColorType(Integer firstColorType) {
        this.firstColorType = firstColorType;
    }

    public Integer getSecondColorType() {
        return secondColorType;
    }

    public void setSecondColorType(Integer secondColorType) {
        this.secondColorType = secondColorType;
    }

    public List<SpotColorBo> getFirstSpotColorList() {
        return firstSpotColorList;
    }

    public void setFirstSpotColorList(List<SpotColorBo> firstSpotColorList) {
        this.firstSpotColorList = firstSpotColorList;
    }

    public List<SpotColorBo> getSecondSpotColorList() {
        return secondSpotColorList;
    }

    public void setSecondSpotColorList(List<SpotColorBo> secondSpotColorList) {
        this.secondSpotColorList = secondSpotColorList;
    }

    @Override
    public String toString() {
        return "ColorBo{" +
                "sided=" + sided +
                ", isGlossOil=" + isGlossOil +
                ", isIdentical=" + isIdentical +
                ", firstColorType=" + firstColorType +
                ", secondColorType=" + secondColorType +
                ", firstSpotColorList=" + firstSpotColorList +
                ", secondSpotColorList=" + secondSpotColorList +
                '}';
    }
}
