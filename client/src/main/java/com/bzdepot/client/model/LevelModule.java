package com.bzdepot.client.model;

public class LevelModule {
    private Long id;

    private Long sellerId;

    private Long levelId;

    private Long userId;

    private Byte userType;

    private String nickname;

    private String username;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getLevelId() {
        return levelId;
    }

    public void setLevelId(Long levelId) {
        this.levelId = levelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Byte getUserType() {
        return userType;
    }

    public void setUserType(Byte userType) {
        this.userType = userType;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public String toString() {
        return "LevelModule{" +
                "id=" + id +
                ", sellerId=" + sellerId +
                ", levelId=" + levelId +
                ", userId=" + userId +
                ", userType=" + userType +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}