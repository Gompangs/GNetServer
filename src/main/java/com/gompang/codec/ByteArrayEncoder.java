
package com.gompang.codec;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 11.
    blog : http://gompangs.tistory.com/
*/

@Sharable
@Component
public class ByteArrayEncoder extends MessageToMessageEncoder<byte[]> {
    public ByteArrayEncoder() {
    }

    protected void encode(ChannelHandlerContext ctx, byte[] msg, List<Object> out) {
        out.add(Unpooled.wrappedBuffer(msg));
    }
}
