package yichen.yao.netty.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:上午11:09
 */
public class NioTest2 {
    public static void main(String[] args) throws Exception{
        FileInputStream fileInputStream = new FileInputStream("NioTest2.txt");
        FileChannel fileChannel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.remaining() > 0){
            byte b = byteBuffer.get();
            System.out.println("Character: " + (char)b);
        }
        fileInputStream.close();
    }
}
