package yichen.yao.netty.nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午5:37
 */
public class MyTest13 {
    public static void main(String[] args) throws Exception{
        String inputFile = "NioTest13.txt";
        String outputFile = "NioTest13_out.txt";
        RandomAccessFile  inputRandomAccessFile= new RandomAccessFile(inputFile,"r");
        RandomAccessFile  outputRandomAccessFile= new RandomAccessFile(outputFile,"rw");
        long inputLength = new File(inputFile).length();
        FileChannel inputChannel = inputRandomAccessFile.getChannel();
        FileChannel outputChannel = outputRandomAccessFile.getChannel();
        MappedByteBuffer inputData = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        System.out.println("--------------");

        System.out.println("--------------");
        Charset charset = Charset.forName("iso-8859-1");
        CharsetDecoder decoder = charset.newDecoder();
        CharsetEncoder encoder = charset.newEncoder();

        CharBuffer charBuffer = decoder.decode(inputData);
        ByteBuffer outputData = encoder.encode(charBuffer);
        outputChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandomAccessFile.close();
    }
}
