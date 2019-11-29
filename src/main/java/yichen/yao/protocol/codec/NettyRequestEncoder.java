package yichen.yao.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import yichen.yao.protocol.NettyRequest;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午2:03
 */
public class NettyRequestEncoder extends MessageToByteEncoder<NettyRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyRequest nettyRequest, ByteBuf byteBuf) throws Exception {
        NettyRequestCodec.INSTANCE.encode(byteBuf,nettyRequest);
    }
}
