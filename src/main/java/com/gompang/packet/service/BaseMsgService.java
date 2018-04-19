package com.gompang.packet.service;

import com.gompang.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public interface BaseMsgService {
    byte[] heartBeat(ChannelHandlerContext ctx, Packet packet);
}
