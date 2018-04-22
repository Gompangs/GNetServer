package com.gompang.common.domain;

public enum CommonResultCode {

    SUCCESS(0, "SUCCESS"),
    FAIL(-1, "FAIL"),
    ;

    private int code;
    private String msg;

    CommonResultCode(int code, String msg) {
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
