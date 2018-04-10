package com.gompang.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@Component
public class SomeScheduler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(fixedDelay = 60000)
    public void schedule() {
        // some schedule

    }
}
