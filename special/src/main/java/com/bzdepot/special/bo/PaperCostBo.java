package com.bzdepot.special.bo;

import com.bzdepot.special.model.PaperCostWithBLOBs;

import java.util.Arrays;

public class PaperCostBo {

    private PaperCostWithBLOBs[] postData;

    public PaperCostWithBLOBs[] getPostData() {
        return postData;
    }

    public void setPostData(PaperCostWithBLOBs[] postData) {
        this.postData = postData;
    }

    @Override
    public String toString() {
        return "PaperCostBo{" +
                "postData=" + Arrays.toString(postData) +
                '}';
    }
}
