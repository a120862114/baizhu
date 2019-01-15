package com.bzdepot.calculate.model;

import com.bzdepot.calculate.bo.SpotColorBo;

import java.util.List;

public class SpotColorTmpModel {

    private int status;

    private List<SpotColorBo> spotColorBo;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<SpotColorBo> getSpotColorBo() {
        return spotColorBo;
    }

    public void setSpotColorBo(List<SpotColorBo> spotColorBo) {
        this.spotColorBo = spotColorBo;
    }
}
