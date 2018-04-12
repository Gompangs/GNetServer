package com.gompang.dispatcher;

import com.gompang.packet.HeartBeat;
import com.gompang.packet.Packet;
import com.gompang.packet.PacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.ByteBuffer;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 12.
    blog : http://gompangs.tistory.com/
*/

@Component
public class PacketDispatcher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void dispatch(Packet packet) {

        // TODO : type dispatching ..
        if (packet.getType() == PacketType.HEART_BEAT) {
            packet.setPacket(HeartBeat.getRootAsHeartBeat(ByteBuffer.wrap(packet.getBody())));
            logger.info("HEART BEAT PACKET RECEIVED : {}", packet.getPacket());
        }
    }
}
