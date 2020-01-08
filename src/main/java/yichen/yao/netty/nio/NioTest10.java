package yichen.yao.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午2:26
 * Scattering Gathering
 */
public class NioTest10 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(8899);
        serverSocketChannel.socket().bind(address);
        int messageLength = 2 + 3 + 4;
        ByteBuffer[] byteBuffers = new ByteBuffer[]{
                ByteBuffer.allocate(2),
                ByteBuffer.allocate(3),
                ByteBuffer.allocate(4)
        };
        SocketChannel accept = serverSocketChannel.accept();
        while (true){
            int byteReads = 0;
            while (byteReads < messageLength){
                long r = accept.read(byteBuffers);
                byteReads += r;
                System.out.println("bytesRead: " + byteBuffers);

                Arrays.asList(byteBuffers).stream()
                        .map(buffer-> "position: " + buffer.position() + "limit: " +buffer.limit())
                        .forEach(System.out::println);
            }
            Arrays.asList(byteBuffers).forEach(buffer->{
                buffer.flip();
            });
            long bytesWritten = 0;
            while (bytesWritten < messageLength){
                long r = accept.write(byteBuffers);
                bytesWritten += r;
            }
            Arrays.asList(byteBuffers).forEach(buffer->{
                buffer.clear();
            });
            System.out.println("bytesRead: " + byteReads + ", byteWritten: " + bytesWritten +" ,messageLength: "+ messageLength);
        }
    }
}
