package com.gompang.support;

import com.gompang.packet.HeartBeat;
import com.gompang.packet.PacketType;
import com.google.flatbuffers.FlatBufferBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PacketSupport {

    Map<Byte, byte[]> packetMap;

    @PostConstruct
    public void init() {
        packetMap = new ConcurrentHashMap<>();

        packetMap.put(PacketType.HeartBeat, this.makeHeartbeat());
    }

    private byte[] makeHeartbeat() {
        FlatBufferBuilder fbb = new FlatBufferBuilder(1);
        HeartBeat.startHeartBeat(fbb);
        fbb.finish(HeartBeat.endHeartBeat(fbb));
        return fbb.sizedByteArray();
    }

    public byte[] getPacket(byte type) {
        return packetMap.get(type);
    }
}
