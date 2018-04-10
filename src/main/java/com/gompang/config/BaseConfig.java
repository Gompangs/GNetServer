package com.gompang.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@Configuration
@EnableScheduling
public class BaseConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init(){
        logger.info("BaseConfig Init");
    }
}
