package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.response.NettyListGroupMembersResponse;

public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<NettyListGroupMembersResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyListGroupMembersResponse nettyListGroupMembersResponse) {
        System.out.println("群[" + nettyListGroupMembersResponse.getGroupId() + "]中的人包括：" + nettyListGroupMembersResponse.getSessionList());
    }
}
