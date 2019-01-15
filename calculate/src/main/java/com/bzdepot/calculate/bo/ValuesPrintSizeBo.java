package com.bzdepot.calculate.bo;

public class ValuesPrintSizeBo {

    private Integer maxlength;

    private Integer maxwidth;

    private Integer pblength;

    private Integer pbwidth;

    public Integer getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(Integer maxlength) {
        this.maxlength = maxlength;
    }

    public Integer getMaxwidth() {
        return maxwidth;
    }

    public void setMaxwidth(Integer maxwidth) {
        this.maxwidth = maxwidth;
    }

    public Integer getPblength() {
        return pblength;
    }

    public void setPblength(Integer pblength) {
        this.pblength = pblength;
    }

    public Integer getPbwidth() {
        return pbwidth;
    }

    public void setPbwidth(Integer pbwidth) {
        this.pbwidth = pbwidth;
    }

    @Override
    public String toString() {
        return "ValuesPrintSizeBo{" +
                "maxlength=" + maxlength +
                ", maxwidth=" + maxwidth +
                ", pblength=" + pblength +
                ", pbwidth=" + pbwidth +
                '}';
    }
}
