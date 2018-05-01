package client;

import com.gompang.packet.HeartBeat;
import com.google.flatbuffers.FlatBufferBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Random;

class MsgSender implements Runnable {

    private ChannelHandlerContext ctx;

    public MsgSender(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run() {
        while (true) {
            FlatBufferBuilder fbb = new FlatBufferBuilder(1);
            HeartBeat.startHeartBeat(fbb);
            fbb.finish(HeartBeat.endHeartBeat(fbb));

            byte[] bytes = fbb.sizedByteArray();

            try {
                ByteBuf buffer = Unpooled.buffer(bytes.length);
                buffer.writeByte(PacketType.HeartBeat); // packet type
                buffer.writeBytes(bytes); // body

                ctx.writeAndFlush((buffer));
                Thread.sleep(new Random().nextInt(2));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SampleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connected");
        // send random data to server when it connected
        new Thread(new MsgSender(ctx)).start();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("read from server : " + msg.getClass().getName());
//        ctx.writeAndFlush(msg);
//        sendMsg(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}