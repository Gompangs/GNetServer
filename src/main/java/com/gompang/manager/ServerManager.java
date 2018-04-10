package com.gompang.manager;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@Component
public class ServerManager {

    private ChannelGroup channels;
    public ChannelGroup getChannels() {
        return channels;
    }

    @PostConstruct
    public void init(){
        channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }
}
