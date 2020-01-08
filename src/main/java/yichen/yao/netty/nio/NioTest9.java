package yichen.yao.netty.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午2:18
 */
public class NioTest9 {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt","rw");
        FileChannel inChannel = randomAccessFile.getChannel();
        MappedByteBuffer map = inChannel.map(FileChannel.MapMode.READ_WRITE,0,5);
        map.put(0,(byte)'a');
        map.put(3,(byte)'b');
        inChannel.close();
    }
}
