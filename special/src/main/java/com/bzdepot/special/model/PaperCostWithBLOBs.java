package com.bzdepot.special.model;

public class PaperCostWithBLOBs extends PaperCost {
    private String drumIds;

    private String drumValues;

    private String otherSizeIds;

    private String otherSizeValues;

    public String getDrumIds() {
        return drumIds;
    }

    public void setDrumIds(String drumIds) {
        this.drumIds = drumIds == null ? null : drumIds.trim();
    }

    public String getDrumValues() {
        return drumValues;
    }

    public void setDrumValues(String drumValues) {
        this.drumValues = drumValues == null ? null : drumValues.trim();
    }

    public String getOtherSizeIds() {
        return otherSizeIds;
    }

    public void setOtherSizeIds(String otherSizeIds) {
        this.otherSizeIds = otherSizeIds == null ? null : otherSizeIds.trim();
    }

    public String getOtherSizeValues() {
        return otherSizeValues;
    }

    public void setOtherSizeValues(String otherSizeValues) {
        this.otherSizeValues = otherSizeValues == null ? null : otherSizeValues.trim();
    }

    @Override
    public String toString() {
        return "PaperCostWithBLOBs{" +
                "drumIds='" + drumIds + '\'' +
                ", drumValues='" + drumValues + '\'' +
                ", otherSizeIds='" + otherSizeIds + '\'' +
                ", otherSizeValues='" + otherSizeValues + '\'' +
                '}';
    }
}