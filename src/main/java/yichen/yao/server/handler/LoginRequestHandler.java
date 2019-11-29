package yichen.yao.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.request.NettyLoginRequest;
import yichen.yao.protocol.response.NettyLoginResponse;
import yichen.yao.session.Session;
import yichen.yao.util.SessionUtil;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午2:32
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<NettyLoginRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyLoginRequest nettyLoginRequest) throws Exception {
        NettyLoginResponse nettyLoginResponse = new NettyLoginResponse();
        nettyLoginResponse.setUserName(nettyLoginRequest.getUsername());
        if(valid(nettyLoginRequest)){
            nettyLoginResponse.setSuccess(true);
            String userId = randomUserId();
            nettyLoginResponse.setUserId(userId);
            System.out.println("[" + nettyLoginRequest.getUsername() + "]登录成功");
            SessionUtil.bindSession(new Session(userId, nettyLoginRequest.getUsername()), ctx.channel());
        }else {
            nettyLoginResponse.setSuccess(false);
            nettyLoginResponse.setReason("账号密码错误");
            System.out.println(new Date() + ": 登录失败!");
        }
        // 登录响应
        ctx.channel().writeAndFlush(nettyLoginResponse);
    }

    private static String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    private boolean valid(NettyLoginRequest loginRequest){
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
