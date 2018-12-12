package com.bzdepot.calculate.bo;

public class PrintSizeBo {

    private Integer pblength;

    private Integer pbwidth;

    private Integer minlength;

    private Integer minwidth;

    private Integer maxlength;

    private Integer maxwidth;

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

    public Integer getMinlength() {
        return minlength;
    }

    public void setMinlength(Integer minlength) {
        this.minlength = minlength;
    }

    public Integer getMinwidth() {
        return minwidth;
    }

    public void setMinwidth(Integer minwidth) {
        this.minwidth = minwidth;
    }

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

    @Override
    public String toString() {
        return "PrintSizeBo{" +
                "pblength=" + pblength +
                ", pbwidth=" + pbwidth +
                ", minlength=" + minlength +
                ", minwidth=" + minwidth +
                ", maxlength=" + maxlength +
                ", maxwidth=" + maxwidth +
                '}';
    }
}
