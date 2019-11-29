package yichen.yao.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import yichen.yao.protocol.request.NettyQuitGroupRequest;
import yichen.yao.protocol.response.NettyQuitGroupResponse;
import yichen.yao.util.SessionUtil;

public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<NettyQuitGroupRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyQuitGroupRequest quitGroupRequest) {
        // 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
        String groupId = quitGroupRequest.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        // 2. 构造退群响应发送给客户端
        NettyQuitGroupResponse nettyQuitGroupResponse = new NettyQuitGroupResponse();

        nettyQuitGroupResponse.setGroupId(quitGroupRequest.getGroupId());
        nettyQuitGroupResponse.setSuccess(true);
        ctx.channel().writeAndFlush(nettyQuitGroupResponse);

    }
}
