package yichen.yao.netty.sixthProtobExample;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: lancer.yao
 * @time: 2020/1/6:下午4:22
 */
public class TestServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage>{

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {
        MyDataInfo.MyMessage.DataType dataType = msg.getDataType();
        if(dataType == MyDataInfo.MyMessage.DataType.StudentType){
            MyDataInfo.Student student = msg.getStudent();
            System.out.println(student.getName());
        }else if(dataType == MyDataInfo.MyMessage.DataType.DogType){
            MyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog.getName());
        }else {
            MyDataInfo.Cat cat = msg.getCat();
            System.out.println(cat.getName());
        }
    }
}
