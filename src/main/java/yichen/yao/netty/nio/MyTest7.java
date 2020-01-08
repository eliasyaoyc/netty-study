package yichen.yao.netty.nio;

import java.nio.ByteBuffer;

/**
 * @Author: lancer.yao
 * @time: 2020/1/7:下午1:43
 * 只读buffer。 我们可以随时将一个普通buffer调用asReadOnlyBuffer方法返回一个只读buffer
 * 但不能将一个只读buffer转换为读写buffer
 */
public class MyTest7 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        for(int i = 0; i < byteBuffer.capacity(); ++i){
            byteBuffer.put((byte)i);
        }
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());
        readOnlyBuffer.position(0);

//        readOnlyBuffer.put((byte)2);
    }
}
