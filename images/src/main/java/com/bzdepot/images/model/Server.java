package com.bzdepot.images.model;

public class Server {
    private Long id;

    private String serverHost;

    private String serverPort;

    private Byte isMaster;

    private Long pid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost == null ? null : serverHost.trim();
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort == null ? null : serverPort.trim();
    }

    public Byte getIsMaster() {
        return isMaster;
    }

    public void setIsMaster(Byte isMaster) {
        this.isMaster = isMaster;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}