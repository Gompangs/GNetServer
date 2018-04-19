package com.gompang.packet.service;

import com.gompang.packet.Packet;
import io.netty.channel.ChannelHandlerContext;

public interface AccountMsgService {

    byte[] deviceRegister(ChannelHandlerContext ctx, Packet packet);
    byte[] playerLogin(ChannelHandlerContext ctx, Packet packet);
}
