import ch.qos.logback.core.encoder.ByteArrayUtil;
import com.gompang.packet.HeartBeat;
import com.gompang.packet.PacketType;
import com.google.flatbuffers.FlatBufferBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class SampleClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("connected");
        // send random data to server when it connected
        sendMsg(ctx);
    }

    private void sendMsg(ChannelHandlerContext ctx){
        FlatBufferBuilder fbb = new FlatBufferBuilder(1);
        HeartBeat.startHeartBeat(fbb);
        fbb.finish(HeartBeat.endHeartBeat(fbb));

        byte[] bytes = fbb.sizedByteArray();

        try {
            ByteBuf buffer = Unpooled.buffer(bytes.length);
            buffer.writeByte(PacketType.HEART_BEAT); // packet type
            buffer.writeBytes(bytes); // body

            ctx.writeAndFlush((buffer));
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("read from server : " + msg.getClass().getName());
//        ctx.writeAndFlush(msg);
        sendMsg(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}