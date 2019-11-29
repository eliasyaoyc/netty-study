package yichen.yao.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import yichen.yao.protocol.request.NettyListGroupMembersRequest;
import yichen.yao.protocol.response.NettyListGroupMembersResponse;
import yichen.yao.session.Session;
import yichen.yao.util.SessionUtil;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<NettyListGroupMembersRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyListGroupMembersRequest nettyListGroupMembersRequest) {
        // 1. 获取群的 ChannelGroup
        String groupId = nettyListGroupMembersRequest.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        // 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
        List<Session> sessionList = new ArrayList<>();
        for (Channel channel : channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        // 3. 构建获取成员列表响应写回到客户端
        NettyListGroupMembersResponse nettyListGroupMembersResponse = new NettyListGroupMembersResponse();

        nettyListGroupMembersResponse.setGroupId(groupId);
        nettyListGroupMembersResponse.setSessionList(sessionList);
        ctx.channel().writeAndFlush(nettyListGroupMembersResponse);
    }
}
