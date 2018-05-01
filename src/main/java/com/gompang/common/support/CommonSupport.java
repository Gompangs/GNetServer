package com.gompang.common.support;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CommonSupport {

    public String getUUID(){
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
