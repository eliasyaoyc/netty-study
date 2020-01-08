package yichen.yao.netty.nio;

import java.nio.ByteBuffer;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午1:24
 */
public class MyTest5 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        byteBuffer.putInt(15);
        byteBuffer.putLong(5000000L);
        byteBuffer.putDouble(14.13311);
        byteBuffer.putChar('你');
        byteBuffer.putShort((short)2);
        byteBuffer.putChar('我');
        byteBuffer.flip();
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getDouble());
        System.out.println(byteBuffer.getChar());
        System.out.println(byteBuffer.getShort());
        System.out.println(byteBuffer.getChar());
    }
}
