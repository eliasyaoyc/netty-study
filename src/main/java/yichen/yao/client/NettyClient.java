package yichen.yao.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import yichen.yao.client.console.ConsoleCommandManager;
import yichen.yao.client.console.LoginConsoleCommand;
import yichen.yao.client.handler.*;
import yichen.yao.protocol.codec.NettyRequestCodec;
import yichen.yao.protocol.codec.NettyRequestDecoder;
import yichen.yao.protocol.codec.NettyRequestEncoder;
import yichen.yao.protocol.codec.Spliter;
import yichen.yao.protocol.request.NettyLoginRequest;
import yichen.yao.protocol.request.NettyMessageRequest;
import yichen.yao.util.LoginUtil;
import yichen.yao.util.SessionUtil;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author yao
 * @date 2019/11/28 下午9:06
 */
public class NettyClient {

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
                        // 登录响应处理器
                        pipeline.addLast(new LoginResponseHandler());
                        // 收消息处理器
                        pipeline.addLast(new MessageResponseHandler());
                        // 创建群响应处理器
                        pipeline.addLast(new CreateGroupResponseHandler());
                        // 加群响应处理器
                        pipeline.addLast(new JoinGroupResponseHandler());
                        // 退群响应处理器
                        pipeline.addLast(new QuitGroupResponseHandler());
                        // 获取群成员响应处理器
                        pipeline.addLast(new ListGroupMembersResponseHandler());
                        // 群消息响应
                        pipeline.addLast(new GroupMessageResponseHandler());
                        // 登出响应处理器
                        pipeline.addLast(new LogoutResponseHandler());
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
