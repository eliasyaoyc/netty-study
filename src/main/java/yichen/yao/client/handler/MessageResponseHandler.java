package yichen.yao.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import yichen.yao.protocol.response.NettyMessageResponse;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午2:40
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<NettyMessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, NettyMessageResponse messageResponse) throws Exception {
        String fromUserId = messageResponse.getFromUserId();
        String fromUserName = messageResponse.getFromUserName();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponse
                .getMessage());
    }
}
