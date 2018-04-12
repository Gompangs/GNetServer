package com.gompang.dispatcher;

import com.gompang.domain.TypedMap;
import com.gompang.domain.TypedMapKey;
import com.gompang.packet.HeartBeat;
import com.gompang.packet.Packet;
import com.gompang.packet.PacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.ByteBuffer;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 12.
    blog : http://gompangs.tistory.com/
*/

@Component
public class PacketDispatcher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private TypedMap typedMap;

    @PostConstruct
    public void init() {
        typedMap = new TypedMap();
    }

    private void initPacketMap() {
        for (String type : PacketType.names) {
            typedMap.put(new TypedMapKey<>(type), "");
        }
    }

    public void dispatch(Packet packet) {

        // TODO : type dispatching ..
        if (packet.getType() == PacketType.HeartBeat) {
            packet.setPacket(HeartBeat.getRootAsHeartBeat(ByteBuffer.wrap(packet.getBody())));
            logger.info("HEART BEAT PACKET RECEIVED : {}", packet.getPacket());
        }
    }
}
