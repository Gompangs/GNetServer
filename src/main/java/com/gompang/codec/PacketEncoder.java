package com.gompang.codec;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class PacketEncoder extends ChannelOutboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
        logger.info("write encode ~");
        ctx.write(msg, promise);
    }
}
