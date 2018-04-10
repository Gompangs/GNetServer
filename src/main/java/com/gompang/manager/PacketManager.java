package com.gompang.manager;

import com.gompang.packet.HeartBeat;
import com.gompang.packet.PacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@Component
public class PacketManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() {

    }

    public Object getPacket(byte[] packet) {

        // TODO : get packet from byte array
        int type = this.getPacketType(packet);
        String name = PacketType.name(type);

        // packet type 1 => heart beat => get heart beat class from body(how?)
        logger.debug("packet type : {}", name);

        // TODO : split packet type
        // type = HEART_BEAT
        // make class from body
        byte[] body = this.getPacketBody(packet);
        logger.debug("before : {}, after : {}", packet.length, body.length);

        HeartBeat heartBeat = new HeartBeat();
        HeartBeat.getRootAsHeartBeat(ByteBuffer.wrap(body), heartBeat);
        logger.debug("packet : {}", heartBeat);


        // TODO : create packet class from body

        return null;
    }

    // get packet type from packet
    private int getPacketType(byte[] bytes) {
        return bytes[0];
    }

    // get packet body from raw packet
    private byte[] getPacketBody(byte[] bytes) {
        byte[] body = new byte[bytes.length - 1];
        System.arraycopy(bytes, 1, body, 0, bytes.length - 1);
        return body;
    }


}
