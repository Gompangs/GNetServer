package com.gompang.packet.service;

import com.gompang.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountMsgServiceImpl implements AccountMsgService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public byte[] deviceRegister(ChannelHandlerContext ctx, Packet packet) {
        return new byte[0];
    }

    @Override
    public byte[] playerLogin(ChannelHandlerContext ctx, Packet packet) {
        return new byte[0];
    }
}
