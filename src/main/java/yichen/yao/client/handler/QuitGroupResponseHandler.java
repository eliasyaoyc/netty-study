package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.response.NettyQuitGroupResponse;


public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<NettyQuitGroupResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyQuitGroupResponse quitGroupResponse) {
        if (quitGroupResponse.isSuccess()) {
            System.out.println("退出群聊[" + quitGroupResponse.getGroupId() + "]成功！");
        } else {
            System.out.println("退出群聊[" + quitGroupResponse.getGroupId() + "]失败！");
        }

    }
}
