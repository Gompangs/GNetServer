package com.gompang.handler;

import io.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class BaseOutboundHandler extends ChannelOutboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        logger.debug("{} ==> {}", ctx.channel().localAddress(), msg);
        ctx.write(msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) {
        ctx.flush();
    }
}
