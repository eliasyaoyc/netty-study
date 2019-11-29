package yichen.yao.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.protocol.codec.NettyRequestCodec;
import yichen.yao.protocol.request.NettyLoginRequest;
import yichen.yao.protocol.request.NettyMessageRequest;
import yichen.yao.protocol.response.NettyLoginResponse;
import yichen.yao.protocol.response.NettyMessageResponse;

import java.util.Date;

/**
 * @author yao
 * @date 2019/11/28 下午9:21
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        NettyRequest nettyRequest = NettyRequestCodec.INSTANCE.decode(byteBuf);
        if(nettyRequest instanceof NettyLoginRequest){
            NettyLoginResponse nettyLoginResponse = new NettyLoginResponse();
            //登录
            NettyLoginRequest loginRequest = (NettyLoginRequest) nettyRequest;
            if(valid(loginRequest)){
                nettyLoginResponse.setSuccess(true);
                System.out.println(new Date() + ": 客户端登录成功");
            }else {
                nettyLoginResponse.setSuccess(false);
                System.out.println(new Date() + "账号密码错误");
            }
            ByteBuf responseByteBuf = NettyRequestCodec.INSTANCE.encode(ctx.alloc().ioBuffer(), nettyLoginResponse);
            ctx.channel().writeAndFlush(responseByteBuf);
        }else if(nettyRequest instanceof NettyMessageRequest){
            NettyMessageResponse messageResponse = new  NettyMessageResponse();
            NettyMessageRequest messageRequest = (NettyMessageRequest) nettyRequest;
            System.out.println(new Date() + ": 收到客户端的消息: " + messageRequest.getMessage());
            messageResponse.setMessage("服务端回复【" + messageRequest.getMessage() + "】");
            ByteBuf responseByteBuf = NettyRequestCodec.INSTANCE.encode(ctx.alloc().ioBuffer(), messageResponse);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }
    private boolean valid(NettyLoginRequest loginRequest){
        return true;
    }
}
