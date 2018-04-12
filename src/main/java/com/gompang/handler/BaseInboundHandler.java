package com.gompang.handler;

import com.gompang.manager.PacketManager;
import com.gompang.manager.ServerManager;
import com.gompang.manager.StatisticsManager;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@ChannelHandler.Sharable
@Component
public class BaseInboundHandler extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private StatisticsManager statisticsManager;

    @Autowired
    private ServerManager serverManager;

    @Autowired
    private PacketManager packetManager;

    private PooledByteBufAllocator pooledByteBufAllocator;

    @PostConstruct
    public void init() {
        logger.info("BaseHandler init");
        pooledByteBufAllocator = PooledByteBufAllocator.DEFAULT;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("channel activated {}", ctx.channel());
        serverManager.getChannels().add(ctx.channel());
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        logger.info("channel deactivated {}", ctx.channel());
        serverManager.getChannels().remove(ctx.channel());
        ctx.fireChannelInactive();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        logger.info("{}", msg.getClass().getName());
//        logger.debug("incoming data from {} , toString : {}", ctx.channel(), new String(this.getBytesFromBuf((ByteBuf) msg)), "UTF-8");
        statisticsManager.read(msg);

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
        cause.printStackTrace();
    }
}
