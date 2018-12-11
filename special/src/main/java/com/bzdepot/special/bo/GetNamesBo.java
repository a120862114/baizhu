package com.bzdepot.special.bo;

public class GetNamesBo {

    private String Names;

    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        Names = names;
    }

    @Override
    public String toString() {
        return "GetNamesBo{" +
                "Names='" + Names + '\'' +
                '}';
    }
}
