package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author yao
 * @date 2019/11/28 下午9:16
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //登录认证

        ctx.channel().writeAndFlush("你好");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

    }
}
