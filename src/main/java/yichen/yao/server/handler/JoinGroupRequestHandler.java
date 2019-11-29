package yichen.yao.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import yichen.yao.protocol.request.NettyJoinGroupRequest;
import yichen.yao.protocol.response.NettyJoinGroupResponse;
import yichen.yao.util.SessionUtil;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<NettyJoinGroupRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyJoinGroupRequest nettyJoinGroupRequest) {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 添加进去
        String groupId = nettyJoinGroupRequest.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        // 2. 构造加群响应发送给客户端
        NettyJoinGroupResponse nettyJoinGroupResponse = new NettyJoinGroupResponse();

        nettyJoinGroupResponse.setSuccess(true);
        nettyJoinGroupResponse.setGroupId(groupId);
        ctx.channel().writeAndFlush(nettyJoinGroupResponse);
    }
}
