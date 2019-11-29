package yichen.yao.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: lancer.yao
 * @time: 2019/11/29:下午2:02
 */
public class NettyRequestDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List out) {
        out.add(NettyRequestCodec.INSTANCE.decode(in));
    }
}
