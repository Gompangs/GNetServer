package com.gompang.dispatcher;

import com.gompang.packet.Packet;
import com.gompang.packet.PacketType;
import com.gompang.packet.service.AccountMsgService;
import com.gompang.packet.service.BaseMsgService;
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
    private AccountMsgService accountMsgService;

    @Autowired
    private BaseMsgService baseMsgService;

    @PostConstruct
    public void init() {

    }

    public byte[] dispatch(ChannelHandlerContext ctx, Packet packet) {

        byte[] response = new byte[0];

        switch (packet.getType()) {
            case PacketType.HeartBeat: {
                response = baseMsgService.heartBeat(ctx, packet);
                break;
            }
            case PacketType.DeviceRegister: {
                response = accountMsgService.deviceRegister(ctx, packet);
                break;
            }
            case PacketType.PlayerLogin: {
                response = accountMsgService.playerLogin(ctx, packet);
                break;
            }
            default: {
                break;
            }
        }
        return response;
    }
}
