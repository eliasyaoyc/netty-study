package yichen.yao.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import yichen.yao.protocol.request.NettyGroupMessageRequest;
import yichen.yao.protocol.response.NettyGroupMessageResponse;
import yichen.yao.util.SessionUtil;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午7:01
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<NettyGroupMessageRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyGroupMessageRequest msg) throws Exception {
        String toGroupId = msg.getToGroupId();
        NettyGroupMessageResponse nettyGroupMessageResponse = new NettyGroupMessageResponse();
        nettyGroupMessageResponse.setFromGroupId(toGroupId);
        nettyGroupMessageResponse.setFromUser(SessionUtil.getSession(ctx.channel()));
        nettyGroupMessageResponse.setMessage(msg.getMessage());

        // 2. 拿到群聊对应的 channelGroup，写到每个客户端
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);
        channelGroup.writeAndFlush(nettyGroupMessageResponse);
    }
}
