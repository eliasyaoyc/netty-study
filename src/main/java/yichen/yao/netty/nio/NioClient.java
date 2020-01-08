package yichen.yao.netty.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午4:50
 */
public class NioClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("localhost", 8899));

            while (true) {
                selector.select();
                Set<SelectionKey> keySet = selector.selectedKeys();
                for (SelectionKey selectionKey : keySet) {
                    if (selectionKey.isConnectable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();
                        if (client.isConnectionPending()) {
                            client.finishConnect();
                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                            writeBuffer.flip();
                            client.write(writeBuffer);
                            ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>());
                            executor.submit(() -> {
                                while (true) {
                                    writeBuffer.clear();
                                    InputStreamReader input = new InputStreamReader(System.in);
                                    BufferedReader br = new BufferedReader(input);
                                    String sendMessage = br.readLine();
                                    writeBuffer.put(sendMessage.getBytes());
                                    writeBuffer.flip();
                                    client.write(writeBuffer);
                                }
                            });
                        }

                    }else if(selectionKey.isReadable()){
                        SocketChannel socket = (SocketChannel) selectionKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        int read = socket.read(readBuffer);
                        if(read > 0){
                            String receiveMessage = new String(readBuffer.array(),0,read);
                            System.out.println(receiveMessage);
                        }
                    }
                }
                keySet.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
