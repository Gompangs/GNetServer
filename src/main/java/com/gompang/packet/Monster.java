// automatically generated by the FlatBuffers compiler, do not modify

package com.gompang.packet;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Monster extends Table {
  public static Monster getRootAsMonster(ByteBuffer _bb) { return getRootAsMonster(_bb, new Monster()); }
  public static Monster getRootAsMonster(ByteBuffer _bb, Monster obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; }
  public Monster __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public Vec3 pos() { return pos(new Vec3()); }
  public Vec3 pos(Vec3 obj) { int o = __offset(4); return o != 0 ? obj.__assign(o + bb_pos, bb) : null; }
  public short mana() { int o = __offset(6); return o != 0 ? bb.getShort(o + bb_pos) : 150; }
  public boolean mutateMana(short mana) { int o = __offset(6); if (o != 0) { bb.putShort(o + bb_pos, mana); return true; } else { return false; } }
  public short hp() { int o = __offset(8); return o != 0 ? bb.getShort(o + bb_pos) : 100; }
  public boolean mutateHp(short hp) { int o = __offset(8); if (o != 0) { bb.putShort(o + bb_pos, hp); return true; } else { return false; } }
  public String name() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer nameAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public int inventory(int j) { int o = __offset(14); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int inventoryLength() { int o = __offset(14); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer inventoryAsByteBuffer() { return __vector_as_bytebuffer(14, 1); }
  public boolean mutateInventory(int j, int inventory) { int o = __offset(14); if (o != 0) { bb.put(__vector(o) + j * 1, (byte)inventory); return true; } else { return false; } }
  public byte color() { int o = __offset(16); return o != 0 ? bb.get(o + bb_pos) : 2; }
  public boolean mutateColor(byte color) { int o = __offset(16); if (o != 0) { bb.put(o + bb_pos, color); return true; } else { return false; } }
  public Weapon weapons(int j) { return weapons(new Weapon(), j); }
  public Weapon weapons(Weapon obj, int j) { int o = __offset(18); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int weaponsLength() { int o = __offset(18); return o != 0 ? __vector_len(o) : 0; }
  public byte equippedType() { int o = __offset(20); return o != 0 ? bb.get(o + bb_pos) : 0; }
  public boolean mutateEquippedType(byte equipped_type) { int o = __offset(20); if (o != 0) { bb.put(o + bb_pos, equipped_type); return true; } else { return false; } }
  public Table equipped(Table obj) { int o = __offset(22); return o != 0 ? __union(obj, o) : null; }
  public Vec3 path(int j) { return path(new Vec3(), j); }
  public Vec3 path(Vec3 obj, int j) { int o = __offset(24); return o != 0 ? obj.__assign(__vector(o) + j * 12, bb) : null; }
  public int pathLength() { int o = __offset(24); return o != 0 ? __vector_len(o) : 0; }

  public static void startMonster(FlatBufferBuilder builder) { builder.startObject(11); }
  public static void addPos(FlatBufferBuilder builder, int posOffset) { builder.addStruct(0, posOffset, 0); }
  public static void addMana(FlatBufferBuilder builder, short mana) { builder.addShort(1, mana, 150); }
  public static void addHp(FlatBufferBuilder builder, short hp) { builder.addShort(2, hp, 100); }
  public static void addName(FlatBufferBuilder builder, int nameOffset) { builder.addOffset(3, nameOffset, 0); }
  public static void addInventory(FlatBufferBuilder builder, int inventoryOffset) { builder.addOffset(5, inventoryOffset, 0); }
  public static int createInventoryVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startInventoryVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static void addColor(FlatBufferBuilder builder, byte color) { builder.addByte(6, color, 2); }
  public static void addWeapons(FlatBufferBuilder builder, int weaponsOffset) { builder.addOffset(7, weaponsOffset, 0); }
  public static int createWeaponsVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startWeaponsVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static void addEquippedType(FlatBufferBuilder builder, byte equippedType) { builder.addByte(8, equippedType, 0); }
  public static void addEquipped(FlatBufferBuilder builder, int equippedOffset) { builder.addOffset(9, equippedOffset, 0); }
  public static void addPath(FlatBufferBuilder builder, int pathOffset) { builder.addOffset(10, pathOffset, 0); }
  public static void startPathVector(FlatBufferBuilder builder, int numElems) { builder.startVector(12, numElems, 4); }
  public static int endMonster(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishMonsterBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
}

