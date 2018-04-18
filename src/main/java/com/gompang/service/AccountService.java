package com.gompang.service;

import com.gompang.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public interface AccountService {

    byte[] login(ChannelHandlerContext ctx, Packet packet);
    byte[] logout(ChannelHandlerContext ctx, Packet packet);
}
