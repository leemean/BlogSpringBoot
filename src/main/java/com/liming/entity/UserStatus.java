package com.liming.entity;

public enum UserStatus {
    normal("正常"), blocked("封禁");
    private final String info;

    private UserStatus(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }
}
