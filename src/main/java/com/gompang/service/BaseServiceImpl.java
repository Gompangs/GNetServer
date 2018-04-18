package com.gompang.service;

import com.gompang.packet.HeartBeat;
import com.gompang.packet.Packet;
import com.gompang.packet.PacketType;
import com.gompang.support.PacketSupport;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

@Service
public class BaseServiceImpl implements BaseService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PacketSupport packetSupport;

    @Override
    public byte[] heartBeat(ChannelHandlerContext ctx, Packet packet) {
        packet.setPacket(HeartBeat.getRootAsHeartBeat(ByteBuffer.wrap(packet.getBody())));

        // heartbeat from remote client
        return packetSupport.getPacket(PacketType.HeartBeat);
    }
}
