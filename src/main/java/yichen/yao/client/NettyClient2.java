package yichen.yao.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import yichen.yao.client.console.ConsoleCommandManager;
import yichen.yao.client.console.LoginConsoleCommand;
import yichen.yao.client.handler.CreateGroupResponseHandler;
import yichen.yao.client.handler.LoginResponseHandler;
import yichen.yao.client.handler.LogoutResponseHandler;
import yichen.yao.client.handler.MessageResponseHandler;
import yichen.yao.protocol.codec.NettyRequestDecoder;
import yichen.yao.protocol.codec.NettyRequestEncoder;
import yichen.yao.protocol.codec.Spliter;
import yichen.yao.util.SessionUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author yao
 * @date 2019/11/28 下午9:06
 */
public class NettyClient2 {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;
    public static void main(String[] args) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel socketChannel) {
                        ChannelPipeline pipeline = socketChannel.pipeline();
                        pipeline.addLast(new Spliter());
                        pipeline.addLast(new NettyRequestDecoder());
                        pipeline.addLast(new LoginResponseHandler());
                        pipeline.addLast(new LogoutResponseHandler());
                        pipeline.addLast(new MessageResponseHandler());
                        pipeline.addLast(new CreateGroupResponseHandler());
                        pipeline.addLast(new NettyRequestEncoder());
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
                Channel channel = ((ChannelFuture) future).channel();
                //开启控制台线程进行与服务端聊天
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.interrupted()){
                    //如果登录成功就打开控制台
                    if(!SessionUtil.hasLogin(channel)){
                        loginConsoleCommand.exec(scanner,channel);
                    }else {
                        consoleCommandManager.exec(scanner,channel);
                    }
                }
            }
        });
        thread.start();
    }
}
