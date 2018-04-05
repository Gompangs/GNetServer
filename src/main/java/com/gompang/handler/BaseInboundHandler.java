package com.gompang.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@ChannelHandler.Sharable
@Component
public class BaseInboundHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() {
        logger.info("BaseHandler init");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("channel activated {}", ctx.channel());
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info("channel deactivated {}", ctx.channel());
        ctx.fireChannelInactive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.debug("incoming data from {}", ctx.channel());

        // TODO : business logic
        ctx.writeAndFlush(msg);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.error("{} raised exception ==> {}", ctx.channel(), cause.getMessage());
    }
}
