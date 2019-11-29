package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.response.NettyCreateGroupResponse;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午4:28
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<NettyCreateGroupResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyCreateGroupResponse msg) throws Exception {
        System.out.print("群创建成功，id 为[" + msg.getGroupId() + "], ");
        System.out.println("群里面有：" + msg.getUserNameList());
    }
}
