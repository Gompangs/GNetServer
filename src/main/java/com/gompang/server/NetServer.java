package com.gompang.server;

import com.gompang.codec.PacketDecoder;
import com.gompang.codec.PacketEncoder;
import com.gompang.handler.BaseInboundHandler;
import com.gompang.handler.BaseOutboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NetServer {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ServerBootstrap sb;

    @Value("${netty.port}")
    private int port;

    @Value("${netty.threads.worker}")
    private int workerThreads;

    @Value("${netty.threads.acceptor}")
    private int acceptorThreads;

    @Value("${netty.threads.isCoreBased}")
    private boolean isCoreBased;

    @Value("${netty.backlog}")
    private int backlog;

    @Autowired
    private BaseInboundHandler baseInboundHandler;

    @Autowired
    private BaseOutboundHandler baseOutboundHandler;

    @Autowired
    private PacketDecoder packetDecoder;

    @Autowired
    private PacketEncoder packetEncoder;

    @PostConstruct
    public void init() {
        EventLoopGroup acceptGroups;
        EventLoopGroup workGroups;


        // check system supports epoll
        if (Epoll.isAvailable()) {

            logger.info("Epoll Supported and selected");
            acceptGroups = new EpollEventLoopGroup(acceptorThreads);
            workGroups = new EpollEventLoopGroup(workerThreads);

            sb = new ServerBootstrap();
            sb.group(acceptGroups, workGroups)
                    .channel(EpollServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, backlog)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) {
                            ChannelPipeline cp = sc.pipeline();
                            // outbound ↑ , call sequence
                            cp.addLast(baseOutboundHandler);    // (2)
                            cp.addLast(packetEncoder);          // (1)

                            // inbound ↓ , (call sequence)
                            cp.addLast(packetDecoder);          // (1)
                            cp.addLast(baseInboundHandler);     // (2)
                        }
                    });
        } else {

            logger.info("Epoll Not Supported, NIO selected");
            acceptGroups = new NioEventLoopGroup(acceptorThreads);
            workGroups = new NioEventLoopGroup(workerThreads);

            sb = new ServerBootstrap();
            sb.group(acceptGroups, workGroups)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, backlog)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) {
                            ChannelPipeline cp = sc.pipeline();
                            // outbound ↑ , call sequence
                            cp.addLast(baseOutboundHandler);    // (2)
                            cp.addLast(packetEncoder);          // (1)

                            // inbound ↓ , (call sequence)
                            cp.addLast(packetDecoder);          // (1)
                            cp.addLast(baseInboundHandler);     // (2)
                        }
                    });
        }

        try {
            sb.bind(port);
        } catch (Exception e) {
            e.printStackTrace();
            acceptGroups.shutdownGracefully();
            workGroups.shutdownGracefully();
        }
    }
}
