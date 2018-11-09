package com.bzdepot.special.bo;

import com.bzdepot.special.model.PrintingCost;

import java.util.Arrays;

public class PrintingCostBo {

    private PrintingCost[] postData;

    public PrintingCost[] getPostData() {
        return postData;
    }

    public void setPostData(PrintingCost[] postData) {
        this.postData = postData;
    }

    @Override
    public String toString() {
        return "PrintingCostBo{" +
                "postData=" + Arrays.toString(postData) +
                '}';
    }
}
