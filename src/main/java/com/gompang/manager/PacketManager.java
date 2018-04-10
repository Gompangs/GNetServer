package com.gompang.manager;

import com.gompang.packet.PacketType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@Component
public class PacketManager {

    @PostConstruct
    public void init() {

    }

    public Object getPacket(byte[] packet) {

        // TODO : get packet from byte array
        int type = this.getPacketType(packet);
        String name = PacketType.name(type);

        // packet type 1 => heart beat => get heart beat class from body(how?)


        // TODO : split packet type

        // TODO : create packet class from body

        return null;
    }

    // get packet type from packet
    private int getPacketType(byte[] b) {
        return b[3] & 0xFF |
                (b[2] & 0xFF) << 8 |
                (b[1] & 0xFF) << 16 |
                (b[0] & 0xFF) << 24;
    }

}
