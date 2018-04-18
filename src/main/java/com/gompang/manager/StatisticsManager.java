package com.gompang.manager;

import com.gompang.packet.Packet;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicLong;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@Component
public class StatisticsManager {

    // packet accumulator
    private AtomicLong writeAccumulator;
    private AtomicLong readAccumulator;

    private AtomicLong writeCountAccumulator;
    private AtomicLong readCountAccumulator;

    private AtomicLong writeTpsAccumulator;
    private AtomicLong readTpsAccumulator;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // interval for statistics report
    @Value("${netty.report.interval}")
    private int interval;

    @Autowired
    private ServerManager serverManager;

    @Value("${netty.report.use}")
    private boolean reportFlag;

    @PostConstruct
    public void init() {
        this.writeAccumulator = new AtomicLong();
        this.readAccumulator = new AtomicLong();
        this.writeCountAccumulator = new AtomicLong();
        this.readCountAccumulator = new AtomicLong();
        this.readTpsAccumulator = new AtomicLong();
        this.writeTpsAccumulator = new AtomicLong();
    }

    // report specific time for statistics
    @Scheduled(fixedRateString = "${netty.report.interval}", initialDelay = 5000)
    public void report() {
        if (reportFlag) {
            logger.info("== [Statistics report started] ==");
            logger.info("read count : {} , write count : {} , read size : {} , write size : {}"
                    , readCountAccumulator.get(), writeCountAccumulator.get(), formatFileSize(readAccumulator.get()), formatFileSize(writeAccumulator.get()));
            logger.info("current channels : {}", serverManager.getChannels().size());
            logger.info("read tps : {}, write tps : {}", readTpsAccumulator.get() / (interval / 1000), writeTpsAccumulator.get() / (interval / 1000));
            logger.info("== [End of Statistics report] ==");

            this.readTpsAccumulator.set(0L);
            this.writeTpsAccumulator.set(0L);
        }
    }

    public void read(Object object) {
        if (object instanceof Packet) {
            Packet packet = (Packet) object;
            this.readAccumulator.addAndGet(1 + packet.getBody().length);
            this.readCountAccumulator.incrementAndGet();
            this.readTpsAccumulator.incrementAndGet();
        }
    }

    public void write(Object object) {
        if (object instanceof ByteBuf) {
            ByteBuf packet = (ByteBuf) object;
            this.writeAccumulator.addAndGet(packet.readableBytes());
            this.writeCountAccumulator.incrementAndGet();
            this.writeTpsAccumulator.incrementAndGet();
        }
    }

    private String formatFileSize(long size) {
        String hrSize;

        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }
}
