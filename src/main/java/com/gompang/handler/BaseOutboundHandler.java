package com.gompang.handler;

import com.gompang.manager.StatisticsManager;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class BaseOutboundHandler extends ChannelOutboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticsManager statisticsManager;

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        logger.debug("{} ==> {}", ctx.channel().localAddress(), msg);
        ctx.write(msg, promise);
        statisticsManager.write(msg);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
