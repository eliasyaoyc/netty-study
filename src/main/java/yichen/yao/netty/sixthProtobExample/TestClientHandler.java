package yichen.yao.netty.sixthProtobExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @Author: lancer.yao
 * @time: 2020/1/6:下午4:30
 */
public class TestClientHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomInt = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if(0 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.StudentType).
                    setStudent(MyDataInfo.Student.newBuilder().setName("张三").setEmail("121").setId(1))
                    .build();
        }else if(1 == randomInt){
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.DogType).
                    setDog(MyDataInfo.Dog.newBuilder().setName("狗"))
                    .build();
        }else {
            myMessage = MyDataInfo.MyMessage.newBuilder().
                    setDataType(MyDataInfo.MyMessage.DataType.CatType).
                    setCat(MyDataInfo.Cat.newBuilder().setName("猫"))
                    .build();
        }
        ctx.channel().writeAndFlush(myMessage);
    }
}
