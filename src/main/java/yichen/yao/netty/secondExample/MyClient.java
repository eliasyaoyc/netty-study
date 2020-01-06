package yichen.yao.netty.secondExample;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: lancer.yao
 * @time: 2020/1/6:上午11:43
 */
public class MyClient {
    public static void main(String[] args) {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture channelFuture = bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer())
                    .connect("localhost", 8899).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            eventExecutors.shutdownGracefully();
        }
    }
}
