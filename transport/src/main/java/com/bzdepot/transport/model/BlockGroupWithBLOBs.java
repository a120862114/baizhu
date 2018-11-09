package com.bzdepot.transport.model;

public class BlockGroupWithBLOBs extends BlockGroup {
    private String cityids;

    private String cityname;

    private String pid;

    public String getCityids() {
        return cityids;
    }

    public void setCityids(String cityids) {
        this.cityids = cityids == null ? null : cityids.trim();
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    @Override
    public String toString() {
        return "BlockGroupWithBLOBs{" +
                "cityids='" + cityids + '\'' +
                ", cityname='" + cityname + '\'' +
                ", pid='" + pid + '\'' +
                '}';
    }
}