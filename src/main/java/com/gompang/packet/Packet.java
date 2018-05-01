package com.gompang.packet;

/*
    Created By Gompangs(stacks5978) at 2018. 4. 12.
    blog : http://gompangs.tistory.com/
*/
@com.gompang.annotation.Packet
public class Packet<T> {
    private byte type;
    private byte[] body;
    private T packet;

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

    public T getPacket() {
        return packet;
    }

    public void setPacket(T packet) {
        this.packet = packet;
    }
}
