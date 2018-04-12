package com.gompang.packet;

import com.gompang.packet.HeartBeat;
import com.gompang.packet.PacketType;
import com.google.flatbuffers.Table;

import java.nio.ByteBuffer;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 12.
    blog : http://gompangs.tistory.com/
*/
public class Packet {
    private byte type;
    private byte[] body;
    private Object packet;

    public Packet(byte type, byte[] body) {
        this.type = type;
        this.body = body;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public Object getPacket() {
        return packet;
    }

    public void setPacket(Object packet) {
        this.packet = packet;
    }
}
