package com.bzdepot.special.model;

public class MachineSize {
    private Long id;

    private String machineName;

    private String machineBs;

    private Long sellerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName == null ? null : machineName.trim();
    }

    public String getMachineBs() {
        return machineBs;
    }

    public void setMachineBs(String machineBs) {
        this.machineBs = machineBs == null ? null : machineBs.trim();
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}