package com.gompang.service;

import com.gompang.packet.HeartBeat;
import com.gompang.packet.Packet;
import com.gompang.support.PacketSupport;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.ByteBuffer;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private PacketSupport packetSupport;

    @Override
    public void heartBeat(ChannelHandlerContext ctx, Packet packet) {
        packet.setPacket(HeartBeat.getRootAsHeartBeat(ByteBuffer.wrap(packet.getBody())));

        // heartbeat from remote client
    }
}
