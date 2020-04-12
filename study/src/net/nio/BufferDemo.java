package net.nio;

import java.nio.ByteBuffer;

/**
 * @description:
 * @author: za-hejin
 * @time: 2019/12/30 21:05
 */
public class BufferDemo {
    public static void main(String[] args) {

        //create a byte buffer, capacity is 12 bytes
        //default is write mode
//        ByteBuffer byteBuffer = ByteBuffer.allocate(12);
        //create a direct memory byte buffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(12);
        System.out.println(String.format("initialize: capacity: %s, position: %s, limit:%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));

        //write 5 bytes data
        byteBuffer.put((byte)1);
        byteBuffer.put((byte)2);
        byteBuffer.put((byte)3);
        byteBuffer.put((byte)4);
        byteBuffer.put((byte)5);
        System.out.println(String.format("initialize: capacity: %s, position: %s, limit:%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));

        //switch to read mode
        //attention: without calling flip(), position will be wrong when read data
        System.out.println("begin to read data");
        byteBuffer.flip();
        byte a = byteBuffer.get();
        System.out.println(a);
        byte b = byteBuffer.get();
        System.out.println(b);
        System.out.println(String.format("initialize: capacity: %s, position: %s, limit:%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));

        //clear read data and switch to write mode
        byteBuffer.compact();
        System.out.println(String.format("initialize: capacity: %s, position: %s, limit:%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));
        byteBuffer.put((byte)5);
        byteBuffer.put((byte)6);
        byteBuffer.put((byte)7);
        byteBuffer.put((byte)8);
        byteBuffer.put((byte)9);
        byteBuffer.put((byte)10);
        byteBuffer.put((byte)11);
        System.out.println(String.format("initialize: capacity: %s, position: %s, limit:%s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));

        //byteBuffer.clear() clear all data
    }
}
