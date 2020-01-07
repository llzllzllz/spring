package com.example.demo1.enums;

public enum NotificationEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(0,"回复了评论")
    ;
    private int status;
    private String name;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    NotificationEnum(int status, String name) {
        this.status = status;
        this.name = name;
    }
}
