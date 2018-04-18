package com.gompang.service;

import com.gompang.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public interface BaseService {
    void heartBeat(ChannelHandlerContext ctx, Packet packet);
}
