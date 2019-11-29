package yichen.yao.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.client.handler.LogoutResponseHandler;
import yichen.yao.protocol.request.NettyLoginRequest;
import yichen.yao.protocol.response.NettyLogoutResponse;
import yichen.yao.util.SessionUtil;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:35
 */
public class LogoutRequestHandler extends SimpleChannelInboundHandler<NettyLoginRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyLoginRequest msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        NettyLogoutResponse nettyLogoutResponse = new NettyLogoutResponse();
        nettyLogoutResponse.setSuccess(true);
        ctx.channel().writeAndFlush(nettyLogoutResponse);
    }
}
