package com.gompang.packet.service;

import com.gompang.packet.Packet;
import com.gompang.packet.PacketType;
import com.gompang.support.PacketSupport;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseMsgServiceImpl implements BaseMsgService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PacketSupport packetSupport;

    private byte[] heartbeat;

    @Override
    public byte[] heartBeat(ChannelHandlerContext ctx, Packet packet) {
        // heartbeat from remote client
        return packetSupport.getPacket(PacketType.HeartBeat);
    }
}
