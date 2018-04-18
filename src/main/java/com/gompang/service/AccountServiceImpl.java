package com.gompang.service;

import com.gompang.packet.Packet;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public byte[] login(ChannelHandlerContext ctx, Packet packet) {
        return new byte[0];
    }

    @Override
    public byte[] logout(ChannelHandlerContext ctx, Packet packet) {
        return new byte[0];
    }
}
