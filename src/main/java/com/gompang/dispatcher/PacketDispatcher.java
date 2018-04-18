package com.gompang.dispatcher;

import com.gompang.packet.Packet;
import com.gompang.packet.PacketType;
import com.gompang.service.AccountService;
import com.gompang.service.BaseService;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 12.
    blog : http://gompangs.tistory.com/
*/

@Component
public class PacketDispatcher {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AccountService accountService;

    @Autowired
    private BaseService baseService;

    @PostConstruct
    public void init() {

    }

    public byte[] dispatch(ChannelHandlerContext ctx, Packet packet) {

        byte[] response = new byte[0];

        switch (packet.getType()) {
            case PacketType.HeartBeat: {
                response = baseService.heartBeat(ctx, packet);
                break;
            }
            case PacketType.Login: {
                response = accountService.login(ctx, packet);
                break;
            }
            case PacketType.Logout: {
                response = accountService.logout(ctx, packet);
                break;
            }
            default: {
                break;
            }
        }
        return response;
    }
}
