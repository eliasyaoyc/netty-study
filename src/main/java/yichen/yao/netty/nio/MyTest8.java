package yichen.yao.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午1:55
 */
public class MyTest8 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output txt");
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true){
            buffer.clear();
            int read = inChannel.read(buffer);
            if(-1 == read){
                break;
            }
            buffer.flip();
            outChannel.write(buffer);
        }
        inChannel.close();
        outChannel.close();
    }
}
