package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.response.NettyLogoutResponse;
import yichen.yao.util.SessionUtil;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:28
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<NettyLogoutResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyLogoutResponse msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
