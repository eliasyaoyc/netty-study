package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.response.NettyGroupMessageResponse;
import yichen.yao.session.Session;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午7:00
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<NettyGroupMessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyGroupMessageResponse msg) throws Exception {
        String fromGroupId = msg.getFromGroupId();
        Session fromUser = msg.getFromUser();
        System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + msg.getMessage());
    }
}
