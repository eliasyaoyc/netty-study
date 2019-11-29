package yichen.yao.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import yichen.yao.protocol.codec.NettyRequestCodec;
import yichen.yao.protocol.request.NettyLoginRequest;
import yichen.yao.protocol.response.NettyLoginResponse;
import yichen.yao.protocol.NettyRequest;
import yichen.yao.protocol.response.NettyMessageResponse;
import yichen.yao.util.LoginUtil;

import java.util.Date;

/**
 * @author yao
 * @date 2019/11/28 下午9:16
 */
@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {

    private NettyRequest request;
    private NettyRequestCodec nettyRequestCodec = NettyRequestCodec.INSTANCE;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ": 客户端登录认证开始------");
        if (request != null) {
            ByteBuf byteBuf = ctx.alloc().ioBuffer();
            ByteBuf encode = nettyRequestCodec.encode(byteBuf, request);
            //登录认证
            ctx.channel().writeAndFlush(encode);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;
        NettyRequest nettyRequest = nettyRequestCodec.decode(byteBuf);
        if(nettyRequest instanceof NettyLoginResponse){
            NettyLoginResponse loginResponse = (NettyLoginResponse) nettyRequest;
            if(loginResponse.isSuccess()){
                System.out.println(new Date() + ": 客户端登录成功");
                LoginUtil.markAsLogin(ctx.channel());
            }else {
                System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponse.getReason());
            }
        }else if(nettyRequest instanceof NettyMessageResponse){
            NettyMessageResponse messageResponse = (NettyMessageResponse) nettyRequest;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponse.getMessage());
        }
    }


    public void setUsernamePassword(String account, String password) {
        request = new NettyLoginRequest(account, password);
    }
}
