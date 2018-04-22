package com.gompang.domain;

public enum AuthResultCode {

    LOGIN_SUCCESS(0, "login success"),
    LOGIN_FAIL(-1, "login fail"),
    INVALID_TOKEN(-2, "invalid token"),


    ;

    private int code;
    private String msg;

    AuthResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
};
