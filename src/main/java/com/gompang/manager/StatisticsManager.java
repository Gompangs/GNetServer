package com.gompang.manager;

import com.gompang.server.NetServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class StatisticsManager {

    // packet accumulator
    private AtomicLong writeAccumulator;
    private AtomicLong readAccumulator;

    private AtomicLong writeCountAccumulator;
    private AtomicLong readCountAccumulator;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // interval for statistics report
    private final int interval = 60000;

    @Autowired
    private ServerManager serverManager;

    @Value("${netty.report}")
    private boolean reportFlag;

    @PostConstruct
    public void init() {
        this.writeAccumulator = new AtomicLong();
        this.readAccumulator = new AtomicLong();
        this.writeCountAccumulator = new AtomicLong();
        this.readCountAccumulator = new AtomicLong();
    }

    // report specific time for statistics
    @Scheduled(fixedRate = interval, initialDelay = 5000)
    public void report() {
        if (reportFlag) {
            logger.info("== [Statistics report started] ==");
            logger.info("read count : {} , write count : {} , read bytes : {} , write bytes : {}"
                    , readCountAccumulator.get(), writeCountAccumulator.get(), readAccumulator.get(), writeAccumulator.get());
            logger.info("current channels : {}", serverManager.getChannels().size());
            logger.info("== [End of Statistics report] ==");
        }
    }


    public void read(Object object) {
        if (object instanceof ByteBuf) {
            this.readAccumulator.addAndGet(((ByteBuf) object).readableBytes());
            this.writeCountAccumulator.incrementAndGet();
        }
    }

    public void write(Object object) {
        if (object instanceof ByteBuf) {
            this.writeAccumulator.addAndGet(((ByteBuf) object).readableBytes());
            readCountAccumulator.incrementAndGet();
        }
    }
}
