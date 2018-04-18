package com.gompang.service;

import com.gompang.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public byte[] login(ChannelHandlerContext ctx, Packet packet) {
        return new byte[0];
    }

    @Override
    public byte[] logout(ChannelHandlerContext ctx, Packet packet) {
        return new byte[0];
    }
}
