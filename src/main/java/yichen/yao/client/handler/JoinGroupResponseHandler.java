package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.response.NettyJoinGroupResponse;

public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<NettyJoinGroupResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyJoinGroupResponse nettyJoinGroupResponse) {
        if (nettyJoinGroupResponse.isSuccess()) {
            System.out.println("加入群[" + nettyJoinGroupResponse.getGroupId() + "]成功!");
        } else {
            System.err.println("加入群[" + nettyJoinGroupResponse.getGroupId() + "]失败，原因为：" + nettyJoinGroupResponse.getReason());
        }
    }
}
