package com.gompang.domain;

public enum PlayerStatus {

    BEFORE_REGISTER(1),
    NORMAL(2),
    RESTRICTED(3),
    ;

    private int value;

    PlayerStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
};