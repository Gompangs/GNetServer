package com.gompang.codec;

import com.gompang.packet.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@Component
@ChannelHandler.Sharable
public class PacketDecoder extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        // decode packet
        byte[] received = (byte[]) msg;
        // get packet class
        Packet packet = new Packet(received[0], getBody(received));


        // fire to inbound handler
        ctx.fireChannelRead(packet);
    }


    private byte[] getBody(byte[] origin) {
        byte[] body = new byte[origin.length - 1];
        System.arraycopy(origin, 1, body, 0, body.length);
        return body;
    }
}
