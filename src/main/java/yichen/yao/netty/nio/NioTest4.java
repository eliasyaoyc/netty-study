package yichen.yao.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午12:45
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("input.txt");
        FileOutputStream fileOutputStream = new FileOutputStream("output txt");
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
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
