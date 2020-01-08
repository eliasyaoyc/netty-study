package yichen.yao.netty.nio;

import javax.naming.ldap.Rdn;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午3:47
 */
public class MyTest11 {
    public static void main(String[] args) throws Exception {
        int[] ports = new int[5];
        ports[0] = 5000;
        ports[1] = 5001;
        ports[2] = 5002;
        ports[3] = 5003;
        ports[4] = 5004;
        Selector selector = Selector.open();
//        String name = selector.getClass().getName();
//        System.out.println(name);
        for (int i = 0; i < ports.length; ++i) {
            ServerSocketChannel socket = ServerSocketChannel.open();
            socket.configureBlocking(false);
            ServerSocket serverSocket = socket.socket();
            InetSocketAddress address = new InetSocketAddress(8899);
            serverSocket.bind(address);

            socket.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口： " + ports[i]);
        }
        while (true){
            int numbers = selector.select();
            System.out.println("number: " + numbers);

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            System.out.println("selectedKeys:"+ selectionKeys);
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if(next.isAcceptable()){
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) next.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector,SelectionKey.OP_READ);
                    iterator.remove();
                    System.out.println("获取到客户端连接： " + socketChannel);
                }else if(next.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) next.channel();
                    int byteRead = 0;
                    while (true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        byteBuffer.clear();
                        int read = socketChannel.read(byteBuffer);
                        if(read <= 0){
                            break;
                        }
                        byteBuffer.flip();
                        socketChannel.write(byteBuffer);
                        byteRead += read;
                    }
                    System.out.println("读取：" + byteRead + "，来自于： " + socketChannel);
                    iterator.remove();
                }
            }
        }
    }
}
