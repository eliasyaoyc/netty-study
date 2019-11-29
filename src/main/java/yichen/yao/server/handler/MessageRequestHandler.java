package yichen.yao.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.codec.NettyRequestCodec;
import yichen.yao.protocol.request.NettyMessageRequest;
import yichen.yao.protocol.response.NettyMessageResponse;
import yichen.yao.session.Session;
import yichen.yao.util.SessionUtil;

import java.util.Date;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午2:33
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<NettyMessageRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NettyMessageRequest nettyMessageRequest) throws Exception {
        // 1.拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        // 2.通过消息发送方的会话信息构造要发送的消息
        NettyMessageResponse messageResponse = new NettyMessageResponse();
        messageResponse.setFromUserId(session.getUserId());
        messageResponse.setFromUserName(session.getUserName());
        messageResponse.setMessage(nettyMessageRequest.getMessage());

        // 3.拿到消息接收方的 channel
        Channel toUserChannel = SessionUtil.getChannel(nettyMessageRequest.getToUserId());

        // 4.将消息发送给消息接收方
        if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
            toUserChannel.writeAndFlush(messageResponse);
        } else {
            System.err.println("[" + nettyMessageRequest.getToUserId() + "] 不在线，发送失败!");
        }
    }
}
