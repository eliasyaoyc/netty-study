package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.response.NettyLoginResponse;
import yichen.yao.session.Session;
import yichen.yao.util.SessionUtil;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午2:40
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<NettyLoginResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyLoginResponse nettyLoginResponse) throws Exception {
        String userId = nettyLoginResponse.getUserId();
        String userName = nettyLoginResponse.getUserName();
        if(nettyLoginResponse.isSuccess()){
            System.out.println("[" + userName + "]登录成功，userId 为: " + userId);
            SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
        }else {
            System.out.println("[" + userName + "]登录失败，原因：" + nettyLoginResponse.getReason());

        }
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }
}
